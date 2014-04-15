package com.hongxu.ripple.gateway.style.module;

import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class Module {

	protected String message;
	protected ModuleVO vo;
	
	public Module(SysTemplate sysTemplate, ModuleVO vo) {
		this.vo = vo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ModuleVO getVo() {
		return vo;
	}

	public void setVo(ModuleVO vo) {
		this.vo = vo;
	}
	
	
}
