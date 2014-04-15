package com.hongxu.ripple.gateway.web;

import com.hongxu.ripple.gateway.style.skin.PageSkin;
import com.hongxu.ripplermb.domain.core.po.Organization;

public class BasePageDataBundle {
	
	private Organization org;
	private PageSkin pageSkin;
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public PageSkin getPageSkin() {
		return pageSkin;
	}
	public void setPageSkin(PageSkin pageSkin) {
		this.pageSkin = pageSkin;
	}
	
	
}
