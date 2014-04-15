package com.hongxu.ripple.gateway.vo.permissions;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.hongxu.ripplermb.domain.core.po.PerUsersRoles;

public interface CustomUserDetails extends UserDetails {

	//用户分管的子系统
	public String getSubSystem();
	
	//用户相对应的角色集
	public Set<PerUsersRoles> getPerUsersRoles();

}
