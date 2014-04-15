package com.hongxu.ripple.gateway.web.module;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripple.gateway.federation.subledger.pnp.FederationSubledger;
import com.hongxu.ripple.gateway.federation.subledger.pnp.to.Lines;
import com.hongxu.ripple.gateway.service.SysPaymentService;
import com.hongxu.ripple.gateway.service.UserExtendFieldCfgService;
import com.hongxu.ripple.gateway.service.UserService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.metadata.UserExtendField;
import com.hongxu.ripple.gateway.style.module.portlet.ModulePasswordForgot;
import com.hongxu.ripple.gateway.style.module.portlet.ModulePasswordReset;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleRegister;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleUserAccountHistory;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleUserAccountProfile;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleUserDepositRipple;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWalletBalance;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWalletBinding;
import com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWithdrawRipple;
import com.hongxu.ripple.gateway.web.SessionBundle;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.SysPayment;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfg;
import com.hongxu.ripplermb.domain.core.po.UserRealInfo;
import com.hongxu.ripplermb.domain.core.po.UserWallet;


@Controller(value="portletController")
@RequestMapping(value = PortletController.MODULE_PATH)
public class PortletController extends BaseModuleController {
	
	public final static String MODULE_PATH = MODULE_ROOT + "/portlet";
	
	private FederationSubledger subledger = null;
	
	@Resource
	private SysPaymentService sysPaymentService;
	@Resource
	private UserService userService;
	@Resource
	private UserExtendFieldCfgService userExtendFieldCfgService;
	
	@RequestMapping(value = "/account_profile.html", method = RequestMethod.GET)
	public String accountProfileGet(HttpServletRequest request, Model model)
	{
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		boolean needReset = false;
		
		List<UserExtendField> userExtendFields = null;
		User user = SessionBundle.getUser(request.getSession());
		List<UserExtendFieldCfg> userExtendFieldCfg = 
				userExtendFieldCfgService.findUserExtendFieldCfgList();
		UserRealInfo userInfo = SessionBundle.getUserRealInfo(request.getSession());
		if(userInfo != null) {
			userExtendFields = userService.getUserExtendFields(userInfo);
		}else {
			userExtendFields = new ArrayList<UserExtendField>();
		}
		
		List<UserExtendField> userExtendFieldList = new ArrayList<UserExtendField>();
		for(int i = 0; i < userExtendFieldCfg.size(); i++) {
			UserExtendFieldCfg fieldCfg = userExtendFieldCfg.get(i);
			boolean found = false;
			for(int j = 0; j < userExtendFields.size(); j++) {
				UserExtendField field = userExtendFields.get(j);
				if(field.getFieldName().equals(fieldCfg.getFieldName())) {
					userExtendFieldList.add(field);
					found = true;
					break;
				}
			}
			if(!found) {
				userExtendFieldList.add(new UserExtendField(fieldCfg));
				needReset = true;
			}
		}
		needReset = needReset ? true: isUserRealInfoChangable(userInfo);
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserAccountProfile.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserAccountProfile.VO>() {};
			
		ModuleUserAccountProfile.VO moduleVo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PORTLET_USER_PROFILE,
				typeRef);
		
		ModuleUserAccountProfile module = new ModuleUserAccountProfile(sysTemplate, moduleVo);
		module.setUserId(user.getId());
		if(userInfo != null) {
			module.setUserInfo(userInfo);
		}
		
		module.setUserExtendFields(userExtendFieldList);
		module.setNeedReset(needReset);
		
		model.addAttribute("hongxu_account_verify", module);
		return MODULE_PATH + "/account_profile";
	}
	
	@RequestMapping(value = "/account_history.html", method = RequestMethod.GET)
	public String accountHistory(HttpServletRequest request, Model model)
	{
		User user = SessionBundle.getUser(request.getSession());
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		ModuleUserAccountHistory module = 
				new ModuleUserAccountHistory(sysTemplate, null);
		try{
			if(subledger == null) {
				subledger = new FederationSubledger();
			}
			Lines lines = subledger.getUserTransactionHistory(user.getEmail());
			module.setLines(lines);
		
		}catch(Exception e) {
			e.printStackTrace();
			module.setMessage(e.getMessage());
		}
		
		model.addAttribute("hongxu_account_history", module);
		return MODULE_PATH + "/account_history";
	}
	
	@RequestMapping(value = "/wallet_binding.html", method = RequestMethod.GET)
	public String walletBinding(HttpServletRequest request, Model model)
	{
		User user = SessionBundle.getUser(request.getSession());
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWalletBinding.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWalletBinding.VO>() {};
			
		ModuleUserWalletBinding.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PORTLET_USER_WALLET_BINDING,
				typeRef);
		
		ModuleUserWalletBinding module = 
				new ModuleUserWalletBinding(sysTemplate, moduleVO);
		module.setUser(user);
		
		model.addAttribute("hongxu_portlet_wallet_binding", module);
		return MODULE_PATH + "/user_wallet_binding";
	}
	
	@RequestMapping(value = "/wallet_balance.html", method = RequestMethod.GET)
	public String walletBalance(
			String walletAddress,
			HttpServletRequest request, 
			Model model)
	{
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		User user = SessionBundle.getUser(request.getSession());
		
		ModuleUserWalletBalance module = new ModuleUserWalletBalance(sysTemplate, null);
		module.setUser(user);
		module.setWalletAddress(walletAddress);
		
		model.addAttribute("hongxu_wallet_balance", module);
		return MODULE_PATH + "/user_wallet_balance";
	}
	
	@RequestMapping(value = "/deposit_ripple.html", method = RequestMethod.GET)
	public String depositRipple(HttpServletRequest request,Model model){
		
		SysTemplate sysTemplate = templateService.getSysTemplate();
		
		HttpSession session = request.getSession();
		User user = SessionBundle.getUser(session);
		List<UserWallet> userWalletList = SessionBundle.getUserWalletList(session);
		List<GatewayCurrency> gatewayCurrencyList = 
				SessionBundle.getGatewayCurrencyList(session);
		List<SysPayment> paymentList = sysPaymentService.findSysPaymentList();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserDepositRipple.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserDepositRipple.VO>() {};
			
		ModuleUserDepositRipple.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PORTLET_USER_DEPOSIT_RIPPLE,
				typeRef);
		
		ModuleUserDepositRipple module = new ModuleUserDepositRipple(sysTemplate, moduleVO);
		String depositFee = templateService.getSettingValue(Settings.DESPOIT_FEE);
		module.setDepositFee(depositFee);
		String gatewayHotWalletAddress = 
				configService.getSettingValue(Settings.GATEWAY_HOT_WALLET_ADDRESS);
		module.setGatewayRippleAddress(gatewayHotWalletAddress);
		module.setUser(user);
		module.setUserWalletList(userWalletList);
		module.setGatewayCurrencyList(gatewayCurrencyList);
		module.setPaymentList(paymentList);
		
		model.addAttribute("hongxu_deposit_ripple", module);
		return MODULE_PATH + "/user_deposit_ripple";
	}
	
	@RequestMapping(value = "/withdraw_ripple.html", method = RequestMethod.GET)
	public String withdrawRipple(HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession();
		User user = SessionBundle.getUser(session);
		List<UserWallet> userWalletList = 
				SessionBundle.getUserWalletList(session);
		List<GatewayCurrency> gatewayCurrencyList = 
				SessionBundle.getGatewayCurrencyList(session);
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWithdrawRipple.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleUserWithdrawRipple.VO>() {};
			
		ModuleUserWithdrawRipple.VO moduleVO = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PORTLET_USER_WITHDRAW_RIPPLE,
				typeRef);
		
		ModuleUserWithdrawRipple module = new ModuleUserWithdrawRipple(sysTemplate, moduleVO);
		String withdrawFee = templateService.getSettingValue(Settings.WITHDRAW_FEE);	
		module.setWithdrawFee(withdrawFee);
		String gatewayRippleAddress = 
			configService.getSettingValue(
				Settings.GATEWAY_HOT_WALLET_ADDRESS);
		module.setGatewayRippleAddress(gatewayRippleAddress);
		module.setGatewayCurrencyList(gatewayCurrencyList);
		module.setUser(user);
		module.setUserWalletList(userWalletList);
		model.addAttribute("hongxu_withdraw_ripple", module);
		return MODULE_PATH + "/user_withdraw_ripple";
	}
	
	@RequestMapping(value = "/user_register.html", method = RequestMethod.GET)
	public String registerGet(HttpServletRequest request,Model model){
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleRegister.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModuleRegister.VO>() {};
	
		ModuleRegister.VO vo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PORTLET_REGISTER,
				typeRef);

		ModuleRegister moduleRegister = new ModuleRegister(sysTemplate, vo);
		moduleRegister.setOrg(templateService.getOrg());
		
		model.addAttribute("hongxu_register", moduleRegister);
		return	MODULE_PATH +"/register";
	}
	
	@RequestMapping(value = "/password_forgot.html", method = RequestMethod.GET)
	public String passwordForgotGet(HttpServletRequest request,Model model){
		
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModulePasswordForgot.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModulePasswordForgot.VO>() {};
			
		ModulePasswordForgot.VO moduleVo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PASSWORD_FORGOT,
				typeRef);
		
		ModulePasswordForgot module = new ModulePasswordForgot(sysTemplate, moduleVo);
		
		model.addAttribute("hongxu_password_forgot", module);
		return MODULE_PATH +"/password_forgot";
	}
	

	@RequestMapping(value = "/password_reset.html", method = RequestMethod.GET)
	public String passwordResetGet(
			String email,
			String verifiedCode,
			HttpServletRequest request,
			Model model)
	{	
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
		
		TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModulePasswordReset.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.portlet.ModulePasswordReset.VO>() {};
			
		ModulePasswordReset.VO moduleVo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_PASSWORD_RESET,
				typeRef);
	
		ModulePasswordReset module = new ModulePasswordReset(sysTemplate, moduleVo);
		module.setEmail(email);
		module.setVerifiedCode(verifiedCode);
		
		model.addAttribute("hongxu_password_reset", module);
		return MODULE_PATH +"/password_reset";
	}
	
	private boolean isUserRealInfoChangable(
				UserRealInfo userRealInfo) 
	{
		boolean changeable = 
				StringUtils.isBlank(userRealInfo.getAddress()) ||
				StringUtils.isBlank(userRealInfo.getBirthday()) ||
				StringUtils.isBlank(userRealInfo.getCity()) ||
				StringUtils.isBlank(userRealInfo.getState()) ||
				StringUtils.isBlank(userRealInfo.getCountry()) ||
				StringUtils.isBlank(userRealInfo.getFirstName()) ||
				StringUtils.isBlank(userRealInfo.getLastName()) ||
				StringUtils.isBlank(userRealInfo.getFullName()) ||
				StringUtils.isBlank(userRealInfo.getIdCardBack()) ||
				StringUtils.isBlank(userRealInfo.getIdCardFront()) ;
		
		return changeable;
	}
}
