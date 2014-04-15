package com.hongxu.ripple.gateway.web.manager;
/**
 * MUserController
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.RippleRestService;
import com.hongxu.ripple.gateway.service.RippleRestTradeService;
import com.hongxu.ripple.gateway.service.SysSettingsService;
import com.hongxu.ripple.gateway.service.UserService;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripple.gateway.vo.ripple.Payment;
import com.hongxu.ripple.gateway.vo.ripple.SubmittingResponse;
import com.hongxu.ripplermb.domain.core.po.RippleRestTrade;
import com.hongxu.ripplermb.domain.core.po.SysSettings;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.support.Page;

@Controller
@RequestMapping(value = MUserController.BASE_URL)
public class MUserController  extends MBaseController{

	private static Logger logger = LoggerFactory.getLogger(MUserController.class);

	@Resource
	private UserService userService;
	
	@Resource
	private SysSettingsService sysSettingsService;
	
	@Resource
	private RippleRestService rippleRestService;
	
	@Resource
	private RippleRestTradeService rippleRestTradeService;
	
	
	
	@RequestMapping(value = "/user_list.r", method = {RequestMethod.GET,RequestMethod.POST})
	public String userList(
													String id,
													String email,
													Page<User> page,
													Model model
													) {
		logger.debug("userList().");
		Page<User> pageData = userService.findUserPage(id, email, page.getPageNo(),page.getPageSize());
		model.addAttribute("page", pageData);
		model.addAttribute("id", id);
		model.addAttribute("email", email);
		
		return BASE_URL+"/user_list";
	}
	
	@RequestMapping(value = "/payment.r",method={RequestMethod.GET})
	public String payment(String id,Model model) {
		logger.debug("payment().params:{}.",id);
		model.addAttribute("user", userService.getUser(id));
		ArrayList<String> currencylist = new ArrayList<String>();
		currencylist.add("XRP");
		SysSettings temp = sysSettingsService.getSysSettings("currency");
		if(temp != null) currencylist.add(temp.getAttrValue());
		
		model.addAttribute("currencylist", currencylist);
		return BASE_URL + "/payment";
	}
	
	@RequestMapping(value = "/payment.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String payment(
			String userId,
			String dstAddress,
			String currency,
			BigDecimal value,
			Model model){
		
		logger.debug("payment().params:{}.");
		SysSettings rippleAddress = sysSettingsService.getSysSettings("gateway_ripple_address");
		SysSettings rippleSecret = sysSettingsService.getSysSettings("gateway_ripple_secret");
		
		//DwzResult result = new DwzResult();
		try{
			Payment payment = rippleRestService.preparingPayment(rippleAddress.getAttrValue(), dstAddress, value, currency);
			if(payment != null){
				logger.debug("preparing a payment result:" + payment);
				SubmittingResponse response = rippleRestService.submittingPayment(rippleSecret.getAttrValue(), dstAddress, payment);
				if(response != null && response.success){
					logger.debug("submitting a payment result:" + response);
					RippleRestTrade trade = new RippleRestTrade();
					trade.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					trade.setCreateTime(new Date());
					trade.setConfirmationToken(response.confirmation_token);
					trade.setCurrency(currency);
					trade.setSrcAddress(rippleAddress.getAttrValue());
					trade.setDstAddress(dstAddress);
					trade.setDstAmount(value);
					rippleRestTradeService.addRippleRestTrade(trade);
				    model.addAttribute("result", true);
//					result.setStatusCode("200");
//					result.setNavTabId("BUSINESS_USER_MANAGE");
//					result.setCallbackType("closeCurrent");
//					result.setMessage("Payment Success.");
				}else{
					model.addAttribute("result", false);
//					result.setStatusCode("300");
//					result.setNavTabId("BUSINESS_USER_MANAGE");
//					result.setCallbackType("closeCurrent");
//					result.setMessage("Payment Error.");
				}
			}else{
				model.addAttribute("result", false);
//				result.setStatusCode("300");
//				result.setNavTabId("BUSINESS_USER_MANAGE");
//				result.setCallbackType("closeCurrent");
//				result.setMessage("Payment Error.");
			}
			
		}catch(Exception ex){
			logger.error("Payment error.{}", ex.getMessage(), ex);
			//result.setStatusCode("300");
			//result.setMessage("Payment Exception" + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		model.addAttribute("user", userService.getUser(userId));
		ArrayList<String> currencylist = new ArrayList<String>();
		currencylist.add("XRP");
		SysSettings temp = sysSettingsService.getSysSettings("currency");
		if(temp != null) currencylist.add(temp.getAttrValue());
		
		model.addAttribute("currencylist", currencylist);
		return BASE_URL+"/payment";
	}
}
