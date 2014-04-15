package com.hongxu.ripple.gateway.service.permissions;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.vo.permissions.LoginUser;
import com.hongxu.ripplermb.domain.core.mapper.PerCustomMapper;
import com.hongxu.ripplermb.domain.core.mapper.PerResourcesMapper;
import com.hongxu.ripplermb.domain.core.mapper.PerRolesMapper;
import com.hongxu.ripplermb.domain.core.mapper.PerUsersMapper;
import com.hongxu.ripplermb.domain.core.mapper.PerUsersRolesMapper;
import com.hongxu.ripplermb.domain.core.po.PerResources;
import com.hongxu.ripplermb.domain.core.po.PerUsers;
import com.hongxu.ripplermb.domain.core.po.PerUsersRoles;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Resource
	private PerUsersMapper perUsersMapper;
	@Resource
	private PerResourcesMapper perResourcesMapper;
	@Resource
	private PerUsersRolesMapper perUsersRolesMapper;
	@Resource
	private PerRolesMapper perRolesMapper;
	@Resource
	private PerCustomMapper perCustomMapper;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		if(StringUtils.isEmpty(username)){
			throw new AuthenticationServiceException("用户" + username + "不能为空.");
		}
		PerUsers user = perUsersMapper.selectByPrimaryKey(username);
		if(user == null){
			throw new AuthenticationServiceException("用户" + username + "不存在.");
		}
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		//HashMap<String, List<PerResources>> res = new HashMap<String, List<PerResources>>();
		HashMap<String, PerResources> res = new HashMap<String, PerResources>();
		//得到用户的权限~
		//List<String> list = sysSecurityMapper.loadUserAuthoritiesByUsernameVersion1(username);
        //List<String> list = perCustomMapper.loadUserAuthoritiesByUserAccountVersion2(username);
		List<String> list = perCustomMapper.loadUserResourcesByUserAccount(username);
        //List<String> listDesc = perCustomMapper.loadUserAuthoritiesDescByUserAccount(username);
        
        for (int i = 0; i < list.size(); i++) {
        	String authorityName = list.get(i);
            auths.add(new GrantedAuthorityImpl(authorityName));
            //System.out.println(authorityName + ":" + perResourcesMapper.selectByPrimaryKey(authorityName));
            res.put(authorityName, perResourcesMapper.selectByPrimaryKey(authorityName));
            //List<PerResources> l = new ArrayList<PerResources>();
            //List<String> resList = perCustomMapper.getResourceStringByAuthorityName(authorityName);
            //for(String str:resList){
            	//PerResourcesCriteria prc = new PerResourcesCriteria();
            	//prc.createCriteria().andResourceStringEqualTo(str);
            	//PerResources sr = perResourcesMapper.selectByExample(prc).get(0);
            	//l.add(sr);
           //}
            //res.put(list.get(i), l);
            
        }  
		
		//根据用户名取得一个PerUsers对象，以获取该用户的其他信息。
		return new LoginUser(
        		user.getUserId(), 
        		user.getUserAccount(), 
        		user.getUserName(), 
        		user.getUserPassword(), 
        		user.getUserDesc(), 
        		true, 
        		false, 
        		user.getUserDuty(), 
        		user.getUserDept(), 
        		"", 
        		new HashSet<PerUsersRoles>(0), 
        		true, 
				true, 
				true, 
				auths, 
        		res
        		);
	}
}
