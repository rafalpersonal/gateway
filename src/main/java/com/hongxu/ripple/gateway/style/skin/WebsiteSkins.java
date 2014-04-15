package com.hongxu.ripple.gateway.style.skin;

import java.util.HashMap;
import java.util.Map;

public class WebsiteSkins {
	
	private static final String DEFAULT_PAGE = "Default";
	
	Map<String, PageSkin> pageSkins = new HashMap<String, PageSkin>();

	public Map<String, PageSkin> getPageSkins() {
		return pageSkins;
	}

	public void setPageSkins(Map<String, PageSkin> pageSkins) {
		this.pageSkins = pageSkins;
	}
	
	public void addPageSkin(String pageName, PageSkin pageSkin) {
		this.pageSkins.put(pageName, pageSkin);
	}
	
	public PageSkin getPageSkin(String pageName) {
		return this.pageSkins.get(pageName);
	}
	
	public PageSkin getDefaultPageSkin() {
		return this.pageSkins.get(DEFAULT_PAGE);
	}
}
