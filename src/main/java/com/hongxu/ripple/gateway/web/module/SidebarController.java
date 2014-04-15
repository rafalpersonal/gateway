package com.hongxu.ripple.gateway.web.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarLogin;
import com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter;
import com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserWallet;
import com.hongxu.ripple.gateway.web.SessionBundle;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserWallet;


@Controller(value="sidebarController")
@RequestMapping(value = SidebarController.MODULE_PATH)
public class SidebarController extends BaseModuleController {

	public final static String MODULE_PATH = MODULE_ROOT + "/sidebar";
	
	@RequestMapping(value = "/login_b.html", method = RequestMethod.GET)
	public String login(
			String styleType,
			HttpServletRequest request,
			Model model)
	{
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
	
		TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarLogin.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarLogin.VO>() {};
			
		ModuleSidebarLogin.VO vo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_LOGIN,
				typeRef);
		ModuleSidebarLogin moduleSidebarLogin = new ModuleSidebarLogin(sysTemplate, vo);
		
		model.addAttribute("hongxu_login", moduleSidebarLogin);
		return MODULE_PATH + "/login"; 
	}
	
	@RequestMapping(value = "/user_account.html", method = RequestMethod.GET)
	public String userAccount(
			String styleType,
			String activeItem,
			HttpServletRequest request,
			Model model)
	{	
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
	
		TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO>() {};
			
		ModuleSidebarUserCenter.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_SIDEBAR_USER_ACCOUNT,
				typeRef);
		
		ModuleSidebarUserCenter module = new ModuleSidebarUserCenter(sysTemplate, moduleVO);
		module.setActiveItem(Integer.parseInt(activeItem));
		
		model.addAttribute("hongxu_user_center_sidebar", module);
		return MODULE_PATH + "/user_center_sidebar";
	}
	
	@RequestMapping(value = "/user_wallet.html", method = RequestMethod.GET)
	public String userWallet(
			String styleType,
			int activeItem,
			String walletAddress,
			HttpServletRequest request,
			Model model)
	{	
		User user = SessionBundle.getUser(request.getSession());
		if(user == null) {
			return MODULE_ROOT + "/error_module";
		}
		List<UserWallet> walletList = userService.getUserWalletList(user.getId());
		if(walletList == null) {
			return MODULE_ROOT + "/error_module";
		}
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
	
		TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO>() {};
			
		ModuleSidebarUserCenter.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_SIDEBAR_USER_WALLET,
				typeRef);
		
		ModuleSidebarUserWallet module = new ModuleSidebarUserWallet(sysTemplate, moduleVO);
		module.setWalletList(walletList);
		if(StringUtils.isEmpty(walletAddress)) {
			module.setActiveItem(activeItem);
		}else {
			module.setActiveItem(-1);
		}
		module.setCurrentWalletAddress(walletAddress);
		model.addAttribute("hongxu_user_wallet_sidebar", module);
		return MODULE_PATH + "/user_wallet_sidebar";
	}
	
	@RequestMapping(value = "/user_deposit.html", method = RequestMethod.GET)
	public String userSideBarDeposit(
			String activeItem,
			HttpServletRequest request,
			Model model)
	{	
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
	
		TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO>() {};
			
		ModuleSidebarUserCenter.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_SIDEBAR_USER_DEPOSIT,
				typeRef);
		
		ModuleSidebarUserCenter module = new ModuleSidebarUserCenter(sysTemplate, moduleVO);
		module.setActiveItem(Integer.parseInt(activeItem));
		
		model.addAttribute("hongxu_user_center_sidebar", module);
		return MODULE_PATH + "/user_center_sidebar";
	}
	
	@RequestMapping(value = "/user_withdraw.html", method = RequestMethod.GET)
	public String userWithdraw(
			String activeItem,
			HttpServletRequest request,
			Model model)
	{	
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.sidebar.ModuleSidebarUserCenter.VO>() {};
			
		ModuleSidebarUserCenter.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_SIDEBAR_USER_WITHDRAW,
				typeRef);
		
		ModuleSidebarUserCenter module = new ModuleSidebarUserCenter(sysTemplate, moduleVO);
		module.setActiveItem(Integer.parseInt(activeItem));

		model.addAttribute("hongxu_user_center_sidebar", module);
		return	MODULE_PATH + "/user_center_sidebar";
	}
	
}
