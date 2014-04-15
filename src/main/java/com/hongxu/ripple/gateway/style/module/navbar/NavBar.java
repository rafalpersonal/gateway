package com.hongxu.ripple.gateway.style.module.navbar;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class NavBar extends Module {
	
	public static final String TYPE_NAVBAR_LEFT = "navbar_left";
	public static final String TYPE_NAVBAR_LEFT_NO_LOGO = "navbar_left_no_logo";
	public static final String TYPE_NAVBAR_RIGHT = "navbar_right";
	public static final String TYPE_NAVBAR_RIGHT_NO_LOGO = "navbar_right_no_logo";

	public NavBar(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
}
