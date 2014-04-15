package com.hongxu.ripple.gateway.style.page.user;

import com.hongxu.ripple.gateway.style.page.Page;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageAccountProfile extends Page {

	private String userId = null;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public PageAccountProfile(SysTemplate sysTemplate, PageDataVO vo) {
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
