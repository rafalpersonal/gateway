package com.hongxu.ripple.gateway.style.adapter.website;

import javax.annotation.Resource;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripple.gateway.service.ConfigService;
import com.hongxu.ripple.gateway.service.PageDataService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.adapter.Adapter;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripple.gateway.style.page.PageLogin;
import com.hongxu.ripple.gateway.style.page.PagePasswordForgot;
import com.hongxu.ripple.gateway.style.page.PagePasswordReset;
import com.hongxu.ripple.gateway.style.page.PageRegister;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

@Service
public class WebsiteAdapter extends Adapter {
	
	@Resource
	private PageDataService pageDataService;
	
	public String index(SysTemplate sysTemplate, Model model) {
		
		String templateName = sysTemplate.getName();
		
		if(TemplateService.TYPE_SIMPLIFY.equals(templateName)) {
			
			TypeReference<com.hongxu.ripple.gateway.template.simplify.page.PageIndex.VO> typeRef =
					new TypeReference<com.hongxu.ripple.gateway.template.simplify.page.PageIndex.VO>() {};
			PageDataVO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_INDEX,
					typeRef);
			
			com.hongxu.ripple.gateway.template.simplify.page.PageIndex page = 
					new com.hongxu.ripple.gateway.template.simplify.page.PageIndex(
						sysTemplate, pageDataVo);
			
			model.addAttribute("pageData", page);
			return WebsiteConfig.TEMPLATE_ROOT + "/" + templateName + "/page_index";
			
		}else if(TemplateService.TYPE_TRADITIONAL.equals(templateName)) {
			
			TypeReference<com.hongxu.ripple.gateway.template.traditional.page.PageIndex.VO> typeRef =
					new TypeReference<com.hongxu.ripple.gateway.template.traditional.page.PageIndex.VO>() {};
			PageDataVO pageDataVo = 
					pageDataService.getPageDataVO(
						sysTemplate.getId(),
						WebsiteConfig.PageKeyForDisplay.PAGE_INDEX,
						typeRef);
			
			com.hongxu.ripple.gateway.template.traditional.page.PageIndex page = 
					new com.hongxu.ripple.gateway.template.traditional.page.PageIndex(
						sysTemplate, pageDataVo);
			
			model.addAttribute("pageData", page);
			return WebsiteConfig.TEMPLATE_ROOT + "/" + templateName + "/page_index";
		}	
		
		return unhandlePage(templateName, model);
	}
	
	public String register(SysTemplate sysTemplate, Model model) {
		
		TypeReference<com.hongxu.ripple.gateway.style.page.PageRegister.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.PageRegister.VO>() {};
		PageRegister.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_REGISTER,
					typeRef);
		
		PageRegister page = 
				new com.hongxu.ripple.gateway.style.page.PageRegister(
					sysTemplate, pageDataVo);
			
		model.addAttribute("pageData", page);
		return WebsiteConfig.TEMPLATE_ROOT + "/" + sysTemplate.getName() + "/page_register";
	}
	
	public String login(SysTemplate sysTemplate, Model model) {
		
		TypeReference<com.hongxu.ripple.gateway.style.page.PageLogin.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.PageLogin.VO>() {};
		PageDataVO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_LOGIN,
					typeRef);
			
		PageLogin page = 
				new com.hongxu.ripple.gateway.style.page.PageLogin(
					sysTemplate, pageDataVo);
		
		model.addAttribute("pageData", page);
		return WebsiteConfig.TEMPLATE_ROOT + "/" + sysTemplate.getName() + "/page_login";
	}
	
	public String forgotPassword(SysTemplate sysTemplate, Model model) {
		
		PagePasswordForgot page = new PagePasswordForgot(sysTemplate, null);
		
		model.addAttribute("pageData", page);
		return WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/page_password_forgot";
	}
	
	public String resetPassword(
					SysTemplate sysTemplate, 
					Model model,
					String email, 
					String verifiedCode) 
	{	
		PagePasswordReset page = 
			new com.hongxu.ripple.gateway.style.page.PagePasswordReset(
				sysTemplate, null);
		page.setEmail(email);
		page.setVerifiedCode(verifiedCode);
		
		model.addAttribute("pageData", page);
		return WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/page_password_reset";
	}
}
