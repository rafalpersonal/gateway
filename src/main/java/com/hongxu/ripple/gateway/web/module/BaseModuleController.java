package com.hongxu.ripple.gateway.web.module;

import javax.annotation.Resource;

import com.hongxu.ripple.gateway.service.ConfigService;
import com.hongxu.ripple.gateway.service.PageDataService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.service.UserService;

public class BaseModuleController {
	
	protected static final String MODULE_ROOT = "module"; 
	
	@Resource
	protected ConfigService configService; 
	
	@Resource
	protected PageDataService pageDataService;
	
	@Resource
	protected TemplateService templateService;
	
	@Resource
	protected UserService userService;
}
