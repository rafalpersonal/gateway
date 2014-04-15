package com.hongxu.ripple.gateway.style.page;

import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PagePasswordReset extends Page {

	private String email = null;
	private String verifiedCode = null;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerifiedCode() {
		return verifiedCode;
	}

	public void setVerifiedCode(String verifiedCode) {
		this.verifiedCode = verifiedCode;
	}

	public PagePasswordReset(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}
	
}
