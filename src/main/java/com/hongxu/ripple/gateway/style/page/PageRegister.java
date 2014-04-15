package com.hongxu.ripple.gateway.style.page;

import com.hongxu.ripplermb.domain.core.po.Organization;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageRegister extends Page {
	
	private Organization org = null;
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public PageRegister(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends PageDataVO {
		
	}
}
