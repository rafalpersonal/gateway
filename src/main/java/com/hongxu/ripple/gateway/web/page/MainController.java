package com.hongxu.ripple.gateway.web.page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.adapter.website.SysTextAdapter;
import com.hongxu.ripple.gateway.style.adapter.website.WebsiteAdapter;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

@Controller(value="mainController")
@RequestMapping(value = MainController.PAGE_PATH)
public class MainController extends BasePageController {

	public static final String PAGE_PATH = "/";
	
	@Resource
	private WebsiteAdapter websiteAdapter;
	@Resource
	private SysTextAdapter sysTextAdapter;
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.MAIN_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		return websiteAdapter.index(sysTemplate, model);
	}
	
	@RequestMapping(value = "/legal/{sysTextKey}.html", method = RequestMethod.GET)
	public String legal(
			@PathVariable("sysTextKey") String sysTextKey,
			Model model,
			HttpServletRequest request) 
	{	
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.DEFAULT_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		SysTemplate sysTemplate = 
			templateService.getSysTemplate();
		
		return sysTextAdapter.display(sysTemplate, model, sysTextKey);
	}
	
	@RequestMapping(value = "/message/{messageKey}.html", method = RequestMethod.GET)
	public String message(
			@PathVariable("messageKey") String messageKey,
			Model model,
			HttpServletRequest request) 
	{	
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.DEFAULT_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		SysTemplate sysTemplate = 
			templateService.getSysTemplate();
		
		return sysTextAdapter.display(sysTemplate, model, messageKey);
	}
	
	@RequestMapping(value = "/error/{errorCode}.html", method = RequestMethod.GET)
	public String error(
			@PathVariable("errorCode") String errorCode,
			Model model,
			HttpServletRequest request) 
	{	
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.DEFAULT_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		String message = null;
		String description = null;
		if("404".equals(errorCode)) {
			message = "Oops, That Page Can't be Found! ";
			description = "It looks like nothing was found at the location! Please try other links !";
			
		}else if("500".equals(errorCode)) {
			message = "Oops, Server is wrong, Let's check it, You can try it later ! ";
			description = "It looks like something is wrong, We will check it as soon as possible!";
			
		}else {
			message = errorCode + ": something wrong !";
			description = "It looks like something is wrong, We will check it as soon as possible!";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("description", description);
		return "error/error";
	}
}	
