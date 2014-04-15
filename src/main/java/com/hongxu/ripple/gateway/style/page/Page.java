package com.hongxu.ripple.gateway.style.page;

import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public abstract class Page {
	
	protected SysTemplate sysTemplate = null;
	protected PageDataVO vo;
	
	public Page(SysTemplate sysTemplate, PageDataVO vo) {
		this.sysTemplate = sysTemplate;
		this.vo = vo;
	}

	public PageDataVO getVo() {
		return vo;
	}

	public void setVo(PageDataVO vo) {
		this.vo = vo;
	}

	public SysTemplate getSysTemplate() {
		return sysTemplate;
	}

	public void setSysTemplate(SysTemplate sysTemplate) {
		this.sysTemplate = sysTemplate;
	}

}
