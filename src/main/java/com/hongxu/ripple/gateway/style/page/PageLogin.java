package com.hongxu.ripple.gateway.style.page;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageLogin extends Page {
	
	public PageLogin(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}

	public static class VO extends PageDataVO {
		
		private String title = null;
		private String content = null;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
}

