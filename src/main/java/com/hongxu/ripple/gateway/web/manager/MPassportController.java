package com.hongxu.ripple.gateway.web.manager;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MPassportController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MPassportController.class);
	
	@RequestMapping(value = "/login.r", method = RequestMethod.GET)
	public String login(Model model) {
		logger.debug("登录.");
		return BASE_URL + "/login";
	}
	
	@RequestMapping(value = "/logout.r", method = RequestMethod.GET)
	public String logout() {
		logger.debug("注销登录.");
		return BASE_URL + "/logout";
	}
	
	@RequestMapping(value = "/accessdenied.r", method = {RequestMethod.GET,RequestMethod.POST})
	public String accessDenied(Model model, HttpServletRequest request) {
		logger.debug("拒绝进入.");
		Object errorInfo = request.getAttribute(WebAttributes.ACCESS_DENIED_403);
		String issueErrorInfo = "登录被拒绝，请检查您是否有登录权限及登录IP是否合法。";
		if (errorInfo instanceof AccessDeniedException) {
			errorInfo = (AccessDeniedException) errorInfo;
			issueErrorInfo = ((AccessDeniedException) errorInfo).getMessage();
		}
		model.addAttribute("issueErrorInfo", issueErrorInfo);
		return BASE_URL+"/accessdenied";
	}
	
	@RequestMapping(value = "/sessiontimeout.r", method = RequestMethod.GET)
	public String sessionTimeout() {
		logger.debug("会话失效.");
		return BASE_URL+"/sessiontimeout";
	}
}
