package com.hongxu.ripple.gateway.web.page;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.service.UserExtendFieldCfgService;
import com.hongxu.ripple.gateway.service.UserService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.metadata.UserExtendField;
import com.hongxu.ripple.gateway.style.page.user.PageAccountProfile;
import com.hongxu.ripple.gateway.style.page.user.PageDeposit;
import com.hongxu.ripple.gateway.style.page.user.PageDepositRipple;
import com.hongxu.ripple.gateway.style.page.user.PageWalletBalance;
import com.hongxu.ripple.gateway.style.page.user.PageWalletBinding;
import com.hongxu.ripple.gateway.style.page.user.PageWithdraw;
import com.hongxu.ripple.gateway.style.page.user.PageWithdrawRipple;
import com.hongxu.ripple.gateway.utils.JsonUtil;
import com.hongxu.ripple.gateway.web.SessionBundle;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfg;
import com.hongxu.ripplermb.domain.core.po.UserRealInfo;

@Controller(value="userCenterController")
@RequestMapping(value = UserCenterController.PAGE_PATH)
public class UserCenterController extends BasePageController {

	public static final String PAGE_PATH = "/user_center";
	
	@Resource
	private UserService userService;
	@Resource
	private UserExtendFieldCfgService userExtendFieldCfgService;
	
	@RequestMapping(value = "/account.html")
	public String account(
			HttpServletRequest request,
			Model model) 
	{	
		return accountProfile(request, model);
	}
	
	
	@RequestMapping(value = "/account_profile.html", method = RequestMethod.GET)
	public String accountProfile(
			HttpServletRequest request,
			Model model) 
	{
		User user = SessionBundle.getUser(request.getSession());
		if(user == null) {
			return "redirect:/" + WebsiteConfig.PageUrl.USER_LOGIN_URL;
		}
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();

		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageAccountProfile.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageAccountProfile.VO>() {};
		PageAccountProfile.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_ACCOUNT_PROFILE,
					typeRef);
		
		PageAccountProfile page = 
				new com.hongxu.ripple.gateway.style.page.user.PageAccountProfile(
					sysTemplate, pageDataVo);
		model.addAttribute("pageData", page);
		
		return  WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/user_center/account/page_account_profile";
	}
	
	@RequestMapping(value = "/account_profile_post.html", method = RequestMethod.POST)
	public String accountProfilePost(
			HttpServletRequest request,
			Model model) 
	{
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			
			String userId = multipartRequest.getParameter("userId");
			UserRealInfo userInfo = null;
			boolean hasProfile = true;
			if(StringUtils.isNotBlank(userId)) {
				userInfo = userService.getUserRealInfo(userId);
			}
			if(userInfo == null) {
				hasProfile = false;
				userInfo = new UserRealInfo();
			}
			MultipartFile idCardFront = multipartRequest.getFile("id_card_front");
			MultipartFile idCardBack = multipartRequest.getFile("id_card_back");
			
			File dir = new File(request.getSession().getServletContext().getRealPath("/") + "/upload/" + userId);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			// extend fields
			List<UserExtendField> userExtendFieldList = 
					new ArrayList<UserExtendField>();
			List<UserExtendFieldCfg> userExtendFieldCfgList = 
					userExtendFieldCfgService.findUserExtendFieldCfgList();
			for(int i = 0; i < userExtendFieldCfgList.size(); i++) {
				UserExtendFieldCfg cfg = userExtendFieldCfgList.get(i);
				String fieldInputName = cfg.getFieldInputName();
				String fieldInputType = cfg.getFieldInputType();
				String fieldValue = null;
				if("text".equals(fieldInputType)) {
					fieldValue = multipartRequest.getParameter(fieldInputName);
					
				}else if("file".equals(fieldInputType)){
					MultipartFile mfile = multipartRequest.getFile(fieldInputName);
					if(mfile != null) {
						String extension = FilenameUtils.getExtension(mfile.getOriginalFilename());
						String saveFilename = fieldInputName + "." + extension;
						saveMultipartFile(dir, saveFilename , mfile);
						fieldValue = "/upload/" + userId + "/" + saveFilename ;
					
					}else {
						List<UserExtendField> oldExtendFields = 
								userService.getUserExtendFields(userInfo);
						for(int j = 0; j < oldExtendFields.size(); j++) {
							UserExtendField oldField = oldExtendFields.get(j);
							if(oldField.getFieldInputName().equals(fieldInputName)) {
								fieldValue = oldField.getFieldValue();
								break;
							}
						}
					}
				}
				if(fieldValue != null) {
					UserExtendField field = new UserExtendField(cfg);
					field.setFieldValue(fieldValue);
					userExtendFieldList.add(field);
				}
			}
			
			userInfo.setId(userId);
			String firstName = multipartRequest.getParameter("firstName");
			userInfo.setFirstName(firstName);		
			String lastName = multipartRequest.getParameter("lastName");
			userInfo.setLastName(lastName);
			userInfo.setFullName(firstName + " " + lastName);
			String birthday = multipartRequest.getParameter("birthday");
			userInfo.setBirthday(birthday);
			String phone = multipartRequest.getParameter("phone");
			userInfo.setPhone(phone);
			String address = multipartRequest.getParameter("address");
			userInfo.setAddress(address);
			String city = multipartRequest.getParameter("city");
			userInfo.setCity(city);
			String state = multipartRequest.getParameter("state");
			userInfo.setState(state);
			String country = multipartRequest.getParameter("country");
			userInfo.setCountry(country);
			if(saveMultipartFile(dir, "ID_Card_front.png", idCardFront)) {
				String idCardFrontFilename = "/upload/" + userId + "/ID_Card_front.png";
				userInfo.setIdCardFront(idCardFrontFilename);
			}
			if(saveMultipartFile(dir, "ID_Card_back.png", idCardBack)) {
				String idCardBackFilename = "/upload/" + userId + "/ID_Card_back.png";
				userInfo.setIdCardBack(idCardBackFilename);		
			}	
			userInfo.setExtendFields(JsonUtil.toString(userExtendFieldList));
			
			if(hasProfile) {
				if(!userService.updateUserRealInfo(userInfo)) {
					List<String> messageList = new ArrayList<String>();
					messageList.add("update user info error !");
					model.addAttribute("messageList", messageList);
					return "error";
				}
			}else {
				if(!userService.addUserRealInfo(userInfo)) {
					List<String> messageList = new ArrayList<String>();
					messageList.add("add user info error !");
					model.addAttribute("messageList", messageList);
					return "error";
				}
			}
			
			userInfo = userService.getUserRealInfo(userId);
			SessionBundle.setUserRealInfo(request.getSession(), userInfo);
			
			return "redirect:/" + WebsiteConfig.PageUrl.USER_CENTER_URL;
			
		} catch(Exception e) {
			e.printStackTrace();
			List<String> messageList = new ArrayList<String>();
			messageList.add("[error message]: " + e.getMessage());
			model.addAttribute("messageList", messageList);
			return "error";
		}
	}
	
	@RequestMapping(value = "/account_history.html", method = RequestMethod.GET)
	public String accountHistory(
			HttpServletRequest request,
			Model model) 
	{	
		User user = SessionBundle.getUser(request.getSession());
		if(user == null) {
			return "redirect:/" + WebsiteConfig.PageUrl.USER_LOGIN_URL;
		}
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();

		return  WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/user_center/account/page_account_history";
	}
	
	@RequestMapping(value = "/wallet_binding.html", method = RequestMethod.GET)
	public String walletBinding(
			HttpServletRequest request,
			Model model) 
	{
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWalletBinding.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWalletBinding.VO>() {};
		PageWalletBinding.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_WALLET_BINDING,
					typeRef);
		
		PageWalletBinding page = 
				new com.hongxu.ripple.gateway.style.page.user.PageWalletBinding(
					sysTemplate, pageDataVo);
		model.addAttribute("pageData", page);
		
		return WebsiteConfig.TEMPLATE_ROOT + 
				"/" + sysTemplate.getName() +  
				"/user_center/wallet/page_wallet_binding";
	}
	
	@RequestMapping(value = "/wallet_binding_post.html", method = RequestMethod.POST)
	public String walletBindingPost(
			String userId,
			String walletName,
			String walletAddress,
			HttpServletRequest request,
			Model model) 
	{
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		if(userService.bindingUserWallet(userId, walletName, walletAddress) != 0) {
			List<String> messageList = new ArrayList<String>();
			messageList.add("bind wallet error !");
			model.addAttribute("messageList", messageList);
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWalletBinding.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWalletBinding.VO>() {};
		PageWalletBinding.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_WALLET_BINDING,
					typeRef);
		
		PageWalletBinding page = 
				new com.hongxu.ripple.gateway.style.page.user.PageWalletBinding(
					sysTemplate, pageDataVo);
		model.addAttribute("pageData", page);
		
		return WebsiteConfig.TEMPLATE_ROOT + 
				"/" + sysTemplate.getName() + 
				"/user_center/wallet/page_wallet_binding";
	}
	
	@RequestMapping(value = "/wallet_balance.html", method = RequestMethod.GET)
	public String walletBalance(
			String activeItem,
			String walletAddress,
			HttpServletRequest request,
			Model model)
	{	
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}	
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWalletBalance.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWalletBalance.VO>() {};
		PageWalletBalance.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_WALLET_BALANCE,
					typeRef);
		
		PageWalletBalance page = 
				new com.hongxu.ripple.gateway.style.page.user.PageWalletBalance(
					sysTemplate, pageDataVo);
		page.setWalletAddress(walletAddress);
		model.addAttribute("pageData", page);
		
		return WebsiteConfig.TEMPLATE_ROOT + "/" + 
				sysTemplate.getName() + 
				"/user_center/wallet/page_wallet_balance";
	}
	
	
	@RequestMapping(value = "/deposit.html", method = RequestMethod.GET)
	public String deposit(
			HttpServletRequest request,
			Model model) 
	{	
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageDeposit.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageDeposit.VO>() {};
		PageDeposit.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_DEPOSIT,
					typeRef);
		
		PageDeposit page = 
				new com.hongxu.ripple.gateway.style.page.user.PageDeposit(
					sysTemplate, pageDataVo);
		model.addAttribute("pageData", page);
		
		
		return  WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() +  
					"/user_center/deposit/page_deposit";
	}
	
	@RequestMapping(value = "/deposit_ripple.html", method = RequestMethod.GET)
	public String depositRipple(
			HttpServletRequest request,
			Model model) 
	{	
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageDepositRipple.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageDepositRipple.VO>() {};
		PageDepositRipple.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_DEPOSIT_RIPPLE,
					typeRef);
		
		PageDepositRipple page = 
				new com.hongxu.ripple.gateway.style.page.user.PageDepositRipple(
					sysTemplate, pageDataVo);
		model.addAttribute("pageData", page);
		
		
		return  WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/user_center/deposit/page_deposit_ripple";
	}
	
	@RequestMapping(value = "/withdraw.html", method = RequestMethod.GET)
	public String withdraw(
			HttpServletRequest request,
			Model model) 
	{	
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWithdraw.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWithdraw.VO>() {};
		PageWithdraw.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_WITHDRAW,
					typeRef);
		
		PageWithdraw page = 
				new com.hongxu.ripple.gateway.style.page.user.PageWithdraw(
					sysTemplate, pageDataVo);
		model.addAttribute("pageData", page);
		
		
		return  WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/user_center/withdraw/page_withdraw";
	}
	
	@RequestMapping(value = "/withdraw_ripple.html", method = RequestMethod.GET)
	public String withdrawRipple(
			HttpServletRequest request,
			Model model) 
	{	
		String redirectUrl = null;
		if((redirectUrl = checkUserInfo(request, model)) != null) {
			return redirectUrl;
		}
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		if(!preparePageData(
				TemplateService.orgId, WebsiteConfig.PageKeyForSkin.USER_CENTER_PAGE, 	
				model, request.getSession()))
		{
			return "error";
		}
		
		TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWithdrawRipple.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.page.user.PageWithdrawRipple.VO>() {};
		PageWithdrawRipple.VO pageDataVo = 
				pageDataService.getPageDataVO(
					sysTemplate.getId(),
					WebsiteConfig.PageKeyForDisplay.PAGE_USER_WITHDRAW_RIPPLE,
					typeRef);
		
		PageWithdrawRipple page = 
				new com.hongxu.ripple.gateway.style.page.user.PageWithdrawRipple(
					sysTemplate, pageDataVo);
		
		model.addAttribute("pageData", page);
		
		
		return  WebsiteConfig.TEMPLATE_ROOT + 
					"/" + sysTemplate.getName() + 
					"/user_center/withdraw/page_withdraw_ripple";
	}
	
	private String checkUserInfo(HttpServletRequest request, Model model) {
		
		User user = SessionBundle.getUser(request.getSession());
		UserRealInfo userInfo = SessionBundle.getUserRealInfo(request.getSession());
		if(user == null) {
			return "redirect:/" + WebsiteConfig.PageUrl.USER_LOGIN_URL;
		}
		
		if(userInfo == null) {
			return accountProfile(request, model);
		}
		
		return null;
	}
	
	private boolean saveMultipartFile(
			File dir, 
			String filename,
			MultipartFile multipartFile) 
	{
		InputStream in = null;
		FileOutputStream out = null;
		byte[] buf = new byte[8192];
		
		if(multipartFile == null) {
			return false;
		}
		
		try{
			// card back
			in = multipartFile.getInputStream();
			out = new FileOutputStream(new File(dir, filename));
			while(in.read(buf) > 0) {
				out.write(buf);
			}
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}finally {
			try{
				if(in != null)	in.close();
				if(out != null)	out.close();
			}catch(Exception ignore)
			{}
		}
						
	}
}
