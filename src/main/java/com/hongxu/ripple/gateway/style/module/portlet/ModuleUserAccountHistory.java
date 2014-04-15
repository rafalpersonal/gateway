package com.hongxu.ripple.gateway.style.module.portlet;

import com.hongxu.ripple.gateway.federation.subledger.pnp.to.Lines;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleUserAccountHistory extends Portlet {

	private Lines lines = null;
	
	public ModuleUserAccountHistory(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}

	public Lines getLines() {
		return lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}
	
	
}
