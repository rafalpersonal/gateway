package com.hongxu.ripple.gateway.style.page.user;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.page.Page;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageWalletBalance extends Page {

	private String walletAddress = null;
	
	public String getWalletAddress() {
		return walletAddress;
	}


	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}


	public PageWalletBalance(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}
	
	
	public static class VO extends PageDataVO {
		String title = null;
		String content = null;
		
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
