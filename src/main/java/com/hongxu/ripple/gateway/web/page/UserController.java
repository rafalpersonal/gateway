package com.hongxu.ripple.gateway.web.page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.MailService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.service.UserService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripple.gateway.style.page.PageLogin;
import com.hongxu.ripple.gateway.style.page.PagePasswordForgot;
import com.hongxu.ripple.gateway.style.page.PagePasswordReset;
import com.hongxu.ripple.gateway.style.page.PageRegister;
import com.hongxu.ripple.gateway.utils.IDGenerator;
import com.hongxu.ripple.gateway.utils.MD5Util;
import com.hongxu.ripple.gateway.web.SessionBundle;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserRealInfo;
import com.hongxu.ripplermb.domain.core.po.UserWallet;

@Controller(value="loginController")
@RequestMapping(value = UserController.PAGE_PATH)
public class UserController extends BasePageController {
	
	public static final String PAGE_PATH = "/user";
	
	@Resource
	private UserService userService;
	@Resource
	private MailService mailService;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginGet(
			HttpServletRequest request,
			Model model)
	{
		HttpSession session = request.getSession();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.DEFAULT_PAGE, 	
				model, session))
		{
			return "error";
		}
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
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
		return  WebsiteConfig.TEMPLATE_ROOT + "/" + sysTemplate.getName() + "/page_login";
	}
	
	@RequestMapping(value = "/login_post.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginPost(
			String username,
			String password,
			HttpServletRequest request,
			Model model)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = null;
		UserRealInfo userInfo = null;
		if(StringUtils.isNumeric(username)) {
			user = userService.getUser(username);
		}
		else if(username.indexOf("@") > 0) {
			user = userService.getUserByEmail(username);
		}
		
		if(user == null) {
			map.put(POST_RESULT_CODE, false);
			map.put(POST_RESULT_MESSAGE, "can't find user '" + username + "' !");
			return map;
		}
		
		userInfo = userService.getUserRealInfo(user.getId());
		List<UserWallet> walletList = userService.getUserWalletList(user.getId());
		HttpSession session = request.getSession();
		SessionBundle.setUser(session, user);
		SessionBundle.setUserRealInfo(session, userInfo);
		SessionBundle.setUserWalletList(session, walletList);
		map.put("isOk", true);
		return map;
	}
	
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(
			HttpServletRequest request,
			Model model)
	{
		request.getSession().invalidate();
		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String registerGet(
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
		
		TypeReference<com.hongxu.ripple.gateway.style.page.PageRegister.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.PageRegister.VO>() {};
		PageRegister.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_REGISTER,
					typeRef);
		
		PageRegister pageRegister = 
				new com.hongxu.ripple.gateway.style.page.PageRegister(
					sysTemplate, pageDataVo);
		pageRegister.setOrg(templateService.getOrg());
		
		model.addAttribute("pageData", pageRegister);
		return "/template/" + sysTemplate.getName() + "/page_register";
	}
	
	@RequestMapping(value = "/register_post.html", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerPost(
			String rippleAddress,
			String email,
			String password,
			String password2,
			Model model) 
	{	
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = userService.getUserByEmail(email);
		if(user != null) {
			map.put(POST_RESULT_CODE, false);
			map.put(POST_RESULT_MESSAGE, email + " has registered before !");
			return map;
		}
		
		user = new User();
		user.setId(IDGenerator.getId());
		user.setRippleAddress(rippleAddress);
		user.setEmail(email);
		user.setPassword(password);
		user.setStatus("DISABLE");
		user.setBalance(new BigDecimal(0.0));
		
		if(!userService.addUser(user)) {
			map.put(POST_RESULT_CODE, false);
			map.put(POST_RESULT_MESSAGE, "error on add user !");
			return map;
		}
		
		mailService.verifyUser( 
				user.getId(),
				email);
		
		map.put(POST_RESULT_CODE, true);
		return map;
	}
	
	@RequestMapping(value = "/register_verify/{code}.html", method = RequestMethod.GET)
	public String registerVerify(
			@PathVariable("code") String code,
			Model model) 
	{	
		User user = userService.getUser(code);
		if(user == null) {
			List<String> messageList = new ArrayList<String>();
			messageList.add("error verify code !");
			model.addAttribute("messageList", messageList);
			return "error";
		}
		
		return "redirect:/" + WebsiteConfig.PageUrl.USER_LOGIN_URL;
	}
	
	@RequestMapping(value = "/password_forgot.html", method = RequestMethod.GET)
	public String passwordForgot(
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
		
		PagePasswordForgot page = new PagePasswordForgot(sysTemplate, null);
		
		model.addAttribute("pageData", page);
		return "/template/" + sysTemplate.getName() + "/page_password_forgot";
	}
	
	@RequestMapping(value = "/password_forgot_post.html", method = RequestMethod.POST)
	public String passwordForgotPost(
			String username,
			Model model) 
	{
		try{
			User user = null;
			if(StringUtils.isNumeric(username)) {
				user = userService.getUser(username);
			}
			else if(username.indexOf("@") > 1) {
				user = userService.getUserByEmail(username);
			}
			
			if(user == null) {
				model.addAttribute("message", "can't find user '" + username + "'");
				return "error/error";
			}
			
			String verifiedCode = MD5Util.digest(user.getId());
			mailService.resetPassword(verifiedCode, user.getEmail());
			
			
			return "redirect:/" + WebsiteConfig.PageUrl.PASSWORD_FORGOT_FINISHED_URL;
			
		}catch(Exception e) {
			e.printStackTrace();
			List<String> messageList = new ArrayList<String>();
			messageList.add("[error message]: " + e.getMessage());
			model.addAttribute("messageList", messageList);
			return "error";
		}
	}
	
	@RequestMapping(value = "/password_reset.html", method = RequestMethod.GET)
	public String passwordReset(
			String email,
			String verifiedCode,
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
		
		PagePasswordReset page = 
				new com.hongxu.ripple.gateway.style.page.PagePasswordReset(
					sysTemplate, null);
		page.setEmail(email);
		page.setVerifiedCode(verifiedCode);
		
		model.addAttribute("pageData", page);
		return "/template/" + sysTemplate.getName() + "/page_password_reset";
	}
	
	@RequestMapping(value = "/password_reset_post.html", method = RequestMethod.POST)
	public String passwordResetPost(
			String email,
			String verifiedCode,
			String newPassword,
			String verifiedPassword,
			HttpServletRequest request,Model model)
	{
		try{
			User user = userService.getUserByEmail(email);
			
			if(user == null) {
				model.addAttribute("message", "can't find user '" + email + "'");
				return "error/error";
			}
			
			String md5Code = MD5Util.digest(user.getId());
			if(!md5Code.equals(verifiedCode)) {
				model.addAttribute("message", "error use password reset function !");
				return "error/error";
			}
			
			user.setPassword(newPassword);
			if(!userService.updateUser(user)) {
				model.addAttribute("message", "update user password error !");
				return "error/error";
			}
			
			return "redirect:/" + WebsiteConfig.PageUrl.USER_LOGIN_URL;
			
		}catch(Exception e) {
			e.printStackTrace();
			List<String> messageList = new ArrayList<String>();
			messageList.add("[error message]: " + e.getMessage());
			model.addAttribute("messageList", messageList);
			return "error";
		}
	}
}


