package com.hongxu.ripple.gateway.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.style.metadata.UserExtendField;
import com.hongxu.ripple.gateway.utils.JsonUtil;
import com.hongxu.ripplermb.domain.core.mapper.UserMapper;
import com.hongxu.ripplermb.domain.core.mapper.UserRealInfoMapper;
import com.hongxu.ripplermb.domain.core.mapper.UserWalletMapper;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserCriteria;
import com.hongxu.ripplermb.domain.core.po.UserRealInfo;
import com.hongxu.ripplermb.domain.core.po.UserWallet;
import com.hongxu.ripplermb.domain.core.po.UserWalletCriteria;
import com.hongxu.ripplermb.domain.core.support.Page;

@Service
public class UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserRealInfoMapper userRealInfoMapper;
	
	@Resource
	private UserWalletMapper userWalletMapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public boolean addUser(User user) {
		
		return userMapper.insert(user) > 0;
	}
	
	public User getUser(String username) {
		
		return userMapper.selectByPrimaryKey(username);
	}
	
	public boolean updateUser(User user) {
		
		return userMapper.updateByPrimaryKey(user) > 0;
	}
	
	public User getUserByEmail(String email) {
		
		UserCriteria criteria = new UserCriteria();
		UserCriteria.Criteria cri = criteria.createCriteria();
		cri.andEmailEqualTo(email);
		
		List<User> userList = userMapper.selectByExample(criteria);
		if(userList.size() > 0) {
			return userList.get(0);
		}
		
		return null;
	}
	
	public UserRealInfo getUserRealInfo(String username) {
		
		return userRealInfoMapper.selectByPrimaryKey(username);
	}
	
	public boolean addUserRealInfo(UserRealInfo userRealInfo) {
		
		return userRealInfoMapper.insert(userRealInfo) > 0;
	}
	
	public boolean updateUserRealInfo(UserRealInfo userRealInfo) {
		
		return userRealInfoMapper.updateByPrimaryKeySelective(userRealInfo) > 0;
	}
	
	public List<UserExtendField> getUserExtendFields(UserRealInfo userRealInfo) {
		
		try{
			List<UserExtendField> extendFieldList = 
					new ArrayList<UserExtendField>();
			
			String extendFields = userRealInfo.getExtendFields();
			if(extendFields != null) {
				TypeReference<List<UserExtendField>> typeRef = 
						new TypeReference<List<UserExtendField>>() {};
				
				extendFieldList = JsonUtil.objFromString(extendFields, typeRef);		
				
			}
			
			return extendFieldList;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int bindingUserWallet(
			String userId,
			String walletName,
			String walletAddress)
	{
		try{
			UserWalletCriteria criteria = new UserWalletCriteria();
			UserWalletCriteria.Criteria cri = criteria.createCriteria();
			cri.andUserIdEqualTo(userId);
			cri.andAddressEqualTo(walletAddress);
			
			List<UserWallet> userWalletList = userWalletMapper.selectByExample(criteria);
			if(userWalletList.size() > 0) {
				return 1;
			}
			
			UserWallet userWallet = new UserWallet();
			userWallet.setName(walletName);
			userWallet.setAddress(walletAddress);
			userWallet.setUserId(userId);
			userWallet.setType(1);
			
			if(userWalletMapper.insert(userWallet) > 0) {
				return 0;
			}
		
			return -1;
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<UserWallet> getUserWalletList(
			String userId)
	{
		try{
			UserWalletCriteria criteria = new UserWalletCriteria();
			UserWalletCriteria.Criteria cri = criteria.createCriteria();
			cri.andUserIdEqualTo(userId);
			
			List<UserWallet> walletList = 
					userWalletMapper.selectByExample(criteria);
			
			return walletList;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param email
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<User> findUserPage(
			String id,
			String email,
			int pageNo,
			int pageSize
			){
			logger.debug("findUserPage().");
			//查询条件~
			Page<User> page = new Page<User>(pageSize);
			//ArrayList<User> list = new ArrayList<User>();
			UserCriteria c = new UserCriteria();
			UserCriteria.Criteria cc = c.createCriteria();
			
			if(StringUtils.isNotBlank(id)) cc.andIdLike("%"+id+"%");
			if(StringUtils.isNotBlank(email)) cc.andEmailLike("%"+email+"%");
			
			//分页参数~
			page.setPageNo(pageNo);
			page.setTotalCount(userMapper.countByExample(c));
			c.setMysqlLength(page.getPageSize());
			c.setMysqlOffset(page.getFirst());
			//c.setOrderByClause("create_time DESC");
			//查询结果~
			List<User> polist = userMapper.selectByExample(c);
//			if(polist != null && polist.size()>0){
//				for(User po : polist){
//					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + po );
//					list.add(po);
//				}
//			}
			page.setResult(polist);
			return page;
	}
}
