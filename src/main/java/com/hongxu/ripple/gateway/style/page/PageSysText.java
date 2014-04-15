package com.hongxu.ripple.gateway.style.page;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageSysText extends Page {
	
	private String pageName;
	private String content;
	
	public PageSysText(
			String pageName, 
			SysTemplate sysTemplate, 
			PageSysTextVO vo) 
	{
		super(sysTemplate, vo);
		this.pageName = pageName;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
