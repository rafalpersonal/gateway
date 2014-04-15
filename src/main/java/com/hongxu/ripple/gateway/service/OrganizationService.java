package com.hongxu.ripple.gateway.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.OrganizationMapper;
import com.hongxu.ripplermb.domain.core.po.Organization;

@Service
public class OrganizationService {
	
	@Resource
	private OrganizationMapper organizationMapper;
	
	public Organization getOrgnization(String orgId) {
		
		return organizationMapper.selectByPrimaryKey(orgId);
	}
	
	public boolean editOrgnization(Organization po){
		boolean result = false;
		if(organizationMapper.updateByPrimaryKey(po)>0) result = true;
		return result;
	}
}
