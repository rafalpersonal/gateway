package com.hongxu.ripple.gateway.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.UserExtendFieldCfgMapper;
import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfg;
import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfgCriteria;

@Service
public class UserExtendFieldCfgService {
	
	@Resource
	private UserExtendFieldCfgMapper userExtendFieldCfgMapper;
	
	public boolean addUserExtendField(UserExtendFieldCfg po){
		boolean result = false;
		po.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		if(userExtendFieldCfgMapper.insertSelective(po)>0) result = true;
		return result;
	}
	
	public UserExtendFieldCfg getUserExtendField(String id) {
		return userExtendFieldCfgMapper.selectByPrimaryKey(id);
	}
	
	public boolean deleteUserExtendField(String id){
		boolean result = false;
		if(userExtendFieldCfgMapper.deleteByPrimaryKey(id)>0) result = true;
		return result;
	}
	
	public List<UserExtendFieldCfg> findUserExtendFieldCfgList(){
		UserExtendFieldCfgCriteria c = new UserExtendFieldCfgCriteria();
		c.setOrderByClause("display_order ASC");
		List<UserExtendFieldCfg> list = userExtendFieldCfgMapper.selectByExample(c);
		return list; 
	}
	
	
	
}
