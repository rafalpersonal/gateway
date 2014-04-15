package com.hongxu.ripple.gateway.web.manager;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hongxu.ripple.gateway.service.SysSettingsService;
import com.hongxu.ripple.gateway.utils.Constants.Languages;
import com.hongxu.ripple.gateway.vo.permissions.LoginUser;
import com.hongxu.ripplermb.domain.core.po.SysSettings;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MIndexController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MIndexController.class);
	
	@Resource
	private SysSettingsService sysSettingsService;
	
//	@InitBinder 
//	public void initBinder(WebDataBinder binder) {
//		logger.debug(">>>>>>>>>>>>>>>>初始化参数.");
//	}
	
	@RequestMapping("/index.r")
	public String index(Model model) {
		logger.debug("首页.");
		LoginUser user = getLoginUser();
		model.addAttribute("alist",user.getAuthorities());
		//System.out.println(user.getAuthorities());
		//System.out.println(user.getRes());
		
		SysSettings temp = sysSettingsService.getSysSettings("language");
		String language="";
		switch(Languages.valueOf(temp.getAttrValue())){
			case en_US:
				language = "en";
				break;
			case zh_CN:
				break;
			default:
				break;
		}
		model.addAttribute("language", temp.getAttrValue());
		//logger.debug(">>>>>>>>>>>>>>>>>>>>>" + sysSettingsService.getSysSettings("language").getAttrValue());
		model.addAttribute("resmap", user.getRes());
		return BASE_URL + "/index";
	}
	
	@RequestMapping("/layout_blank.r")
	public String layoutBlank(Model model) {
		logger.debug("layout_blank.");
		return BASE_URL + "/layout_blank";
	}
	
	@RequestMapping("/layout_ajax.r")
	public String layoutAjax(Model model) {
		logger.debug("layout_ajax.");
		return BASE_URL + "/layout_ajax";
	}
	
	@RequestMapping("/blank.r")
	public String blank(String parent,String key,Model model) {
		
		return BASE_URL + "/layout_blank";
	}
	
	@RequestMapping("/menu.r")
	public String menu(String parent,String key,Model model) {
		logger.debug("menu.params:{}.",key);
		LoginUser user = getLoginUser();
		model.addAttribute("alist",user.getAuthorities());
		model.addAttribute("resmap", user.getRes());
		model.addAttribute("key", key);
		return BASE_URL + "/menu";
	}
	
	@RequestMapping("/header.r")
	public String header(String parent,String key,Model model) {
		
		return BASE_URL + "/header";
	}
	
	@RequestMapping("/footer.r")
	public String footer(String parent,String key,Model model) {
		
		return BASE_URL + "/footer";
	}
}
