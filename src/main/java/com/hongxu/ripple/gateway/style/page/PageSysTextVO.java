package com.hongxu.ripple.gateway.style.page;

import com.hongxu.ripplermb.domain.core.po.SysTxt;

public class PageSysTextVO extends PageDataVO {
	
	private SysTxt sysText = null;

	public PageSysTextVO(SysTxt sysText) {
		this.sysText = sysText;
	}
	
	public SysTxt getSysText() {
		return sysText;
	}

	public void setSysText(SysTxt sysText) {
		this.sysText = sysText;
	}
	
	public String getTitle() {
		return sysText.getTxtTitle();
	}
	
	public String getContent() {
		return sysText.getTxtContent();
	}
}
