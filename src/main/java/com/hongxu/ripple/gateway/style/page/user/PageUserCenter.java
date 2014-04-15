package com.hongxu.ripple.gateway.style.page.user;

import com.hongxu.ripple.gateway.domain.Account;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.page.Page;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageUserCenter extends Page {
	
	private Account account = null;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public PageUserCenter(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends PageDataVO {

	}
}
