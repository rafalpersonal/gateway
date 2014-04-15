package com.hongxu.ripple.gateway.style.module.footer;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class Footer extends Module {
	
	public static final String TYPE_LINK_SINGLE_LINE = "single_line";
	public static final String TYPE_LINK_MULTI_LINE = "multi_line";
	public static final String TYPE_INFORMATION = "information";

	public Footer(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
}
