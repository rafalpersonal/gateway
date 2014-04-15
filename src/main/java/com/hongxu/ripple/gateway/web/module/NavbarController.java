package com.hongxu.ripple.gateway.web.module;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripple.gateway.service.ConfigService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.module.navbar.ModuleNavbar;
import com.hongxu.ripple.gateway.style.module.navbar.NavBar;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

@Controller(value="navbarController")
@RequestMapping(value = NavbarController.MODULE_PATH)
public class NavbarController extends BaseModuleController {
	
	public final static String MODULE_PATH = MODULE_ROOT + "/navbar";
	
	@Resource
	private ConfigService configService;
	
	@RequestMapping(value = "/navbar_b.html", method = RequestMethod.GET)
	public String navbar(
			String styleType,
			String activeBtn,
			HttpServletRequest request,
			Model model)
	{	
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.navbar.ModuleNavbar.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.navbar.ModuleNavbar.VO>() {};
			
		ModuleNavbar.VO vo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_NAVBAR,
				typeRef);
		
		if(StringUtils.isEmpty(vo.getLogo())) {
			String logo = configService.getSettingValue(Settings.LOGO);
			vo.setLogo(logo);
		}
		
		ModuleNavbar module = new ModuleNavbar(sysTemplate, vo);
		module.setActiveButton(activeBtn);
		model.addAttribute("hongxu_navbar", module);
		
		if(NavBar.TYPE_NAVBAR_LEFT.equals(styleType)) {
			return MODULE_PATH + "/navbar";
		
		}else if(NavBar.TYPE_NAVBAR_RIGHT.equals(styleType)) {
			return MODULE_PATH + "/navbar";
			
		}else if(NavBar.TYPE_NAVBAR_LEFT_NO_LOGO.equals(styleType)) {
			return MODULE_PATH + "/navbar";
			
		}else if(NavBar.TYPE_NAVBAR_RIGHT_NO_LOGO.equals(styleType)) {
			return MODULE_PATH + "/navbar";
		}
		
		return MODULE_ROOT + "/dummy_module";
	}
	
	
}
