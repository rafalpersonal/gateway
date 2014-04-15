package com.hongxu.ripple.gateway.web.manager;
/**
 * MPaymentRecordController
 */
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jrippleapi.connection.GenericJSONSerializable;
import jrippleapi.connection.RippleAddressPublicInformation;
import jrippleapi.hongxu.RippleWallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripple.gateway.federation.subledger.pnp.FederationSubledger;
import com.hongxu.ripple.gateway.service.ConfigService;
import com.hongxu.ripple.gateway.service.DepositRecordService;
import com.hongxu.ripple.gateway.service.GatewayService;
import com.hongxu.ripple.gateway.service.UserService;
import com.hongxu.ripple.gateway.vo.Deposit;
import com.hongxu.ripplermb.domain.core.po.DepositRecord;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.support.Page;
import com.hongxu.ripplermb.domain.core.support.PaymentStatus;

@Controller
@RequestMapping(value = MDepositRecordController.BASE_URL)
public class MDepositRecordController  extends MBaseController{

	private static Logger logger = LoggerFactory.getLogger(MDepositRecordController.class);

	@Resource
	private DepositRecordService depositRecordService;
	
	
	@Resource
	private ConfigService configService;
	
	@Resource
	private UserService userService;
	@Resource
	private GatewayService gatewayService;
	
	private RippleWallet  wallet = null;
	
	@RequestMapping(value = "/deposit_record_list.r", method = {RequestMethod.GET,RequestMethod.POST})
	public String depositRecordList(
						String userId,
						String sn,
						String pmtStatus,
						Page<Deposit> page,
						Model model
						) {
		logger.debug("depositRecordList().");
		
		Page<Deposit> pageData = depositRecordService.findDepositPage(userId, sn, pmtStatus, null, null, page.getPageNo(),page.getPageSize());
		model.addAttribute("page", pageData);
		model.addAttribute("paymentStatusKeys", PaymentStatus.inverseMapping().keySet());
		model.addAttribute("paymentStatus", PaymentStatus.inverseMapping());
		model.addAttribute("userId", userId);
		model.addAttribute("sn", sn);
		
		return BASE_URL+"/deposit_record_list";
	}
	
	@RequestMapping(value = "/recharge.r",method={RequestMethod.GET})
	public String recharge(String id,Model model) {
		logger.debug("recharge().params:{}.",id);
		model.addAttribute("po", depositRecordService.getDepositRecord(id));
		List<GatewayCurrency> currencyList = 
				gatewayService.getGatewayCurrencyList();
		
		model.addAttribute("currencylist", currencyList);
		return BASE_URL + "/recharge";
	}
	
	@RequestMapping(value = "/recharge.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String recharge(String id,HttpServletRequest request,Model model){
		
		DepositRecord depositRecord = depositRecordService.getDepositRecord(id);
		if(depositRecord == null) {
			logger.debug("Deposit record no exist, id = " + id);
			model.addAttribute("result", false);
			return recharge(id, model);
		}
		User user = userService.getUser(depositRecord.getUserId());
		if(user == null){
			logger.debug("Gateway user no exist.");
			model.addAttribute("result", false);
			return recharge(id, model);
		}
		
		String currency = depositRecord.getCurrency();
		String issuer = depositRecord.getIssuer();
		double amount = depositRecord.getMoney().doubleValue();
		String userRippleAddress = depositRecord.getWalletAddress();
		String gatewayWalletName = 
				configService.getSettingValue(Settings.GATEWAY_HOT_WALLET_NAME);
		String gatewayWalletPassword = 
				configService.getSettingValue(Settings.GATEWAY_HOT_WALLET_PASSWORD);
		String gatewayWalletAddress = 
				configService.getSettingValue(Settings.GATEWAY_HOT_WALLET_ADDRESS);
		
		try {
			if(wallet == null) {
				wallet = new RippleWallet(gatewayWalletName, gatewayWalletPassword);
			}
			wallet.open();
			
			RippleAddressPublicInformation accountInfo = wallet.getAccountInfo();
			if(!wallet.sendIOU(
					currency, 
					amount, 
					issuer,
					userRippleAddress, 
					accountInfo.nextTransactionSequence
					))
			{
				model.addAttribute("result", false);
				return recharge(id, model);
			}			
			
			//变更payment记录状态~
			depositRecord.setPmtUser(gatewayWalletAddress);
			depositRecord.setPmtTime(new Date());
			depositRecord.setPmtStatus(PaymentStatus.PAYMENT_SUCCESS.toString());
			if(depositRecordService.editDepositRecord(depositRecord)){
				//记账~
				//subledger记账加在此处~
				FederationSubledger fs = new FederationSubledger();
				fs.userDepositFund(
						UUID.randomUUID().toString(), 
						user.getEmail(), 
						depositRecord.getTotal().toString(), 
						depositRecord.getMoney().toString(), 
						depositRecord.getFee().toString());
				model.addAttribute("result", true);
			}else{
				model.addAttribute("result", false);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("result", false);
		}

		return recharge(id, model);
	}
}
