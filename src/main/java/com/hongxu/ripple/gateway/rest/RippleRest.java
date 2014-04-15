package com.hongxu.ripple.gateway.rest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jrippleapi.connection.DenominatedIssuedCurrency;
import jrippleapi.connection.RippleAddress;
import jrippleapi.connection.RippleAddressPublicInformation;
import jrippleapi.connection.RippleTransactionResult;
import jrippleapi.connection.RippleTxPaymentResult;
import jrippleapi.connection.TrustLine;
import jrippleapi.connection.TrustLines;
import jrippleapi.hongxu.RippleWallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripple.gateway.federation.subledger.pnp.FederationSubledger;
import com.hongxu.ripple.gateway.service.ConfigService;
import com.hongxu.ripple.gateway.service.DepositRecordService;
import com.hongxu.ripple.gateway.service.GatewayService;
import com.hongxu.ripple.gateway.service.MailService;
import com.hongxu.ripple.gateway.service.RippleQueryService;
import com.hongxu.ripple.gateway.service.UserService;
import com.hongxu.ripple.gateway.service.WithdrawRecordService;
import com.hongxu.ripple.gateway.utils.IDGenerator;
import com.hongxu.ripplermb.domain.core.po.DepositRecord;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.User;

@Service(value="rippleRest")
@Path("ripple")
public class RippleRest {
	
	private Logger logger = LoggerFactory.getLogger(RippleRest.class);

	private RippleWallet wallet = null;
	
	@Resource
	private RippleQueryService rippleQueryService;
	@Resource
	private UserService userService;
	@Resource
	private MailService mailService;
	@Resource
	private DepositRecordService depositRecordService;
	@Resource
	private WithdrawRecordService withdrawRecordService;
	@Resource
	private ConfigService configService;
	@Resource
	private GatewayService gatewayService; 
	
	private FederationSubledger subledger = null;
	
//	@POST
//	@Path("/accountInfo")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Map<String, Object> accountInfo(
//			@FormParam("userId") String userId) 
//	{
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		User user = userService.getUser(userId);
//		
//		String gatewayCurrency = configService.getSettingValue(Settings.CURRENCY);
//		String gatewayRippleAddress = configService.getSettingValue(Settings.RIPPLE_ADDRESS);
//		
//		RippleAddressPublicInformation pubInfo = 
//				rippleQueryService.getAccountInfo(user.getRippleAddress());
//		
//		TrustLines trustLines = 
//				rippleQueryService.getTrustLines(user.getRippleAddress());
//		
//		UserBalance userBalance = new UserBalance();
//		userBalance.setUser(user);
//		userBalance.addCurrency(new DenominatedIssuedCurrency(pubInfo.xrpBalance));
//		for(TrustLine trustLine : trustLines) {
//			if(trustLine.otherAccount.equals(gatewayRippleAddress)) {
//				DenominatedIssuedCurrency currency = 
//					new DenominatedIssuedCurrency(trustLine.balance,
//							new RippleAddress(trustLine.otherAccount),
//							trustLine.currency);
//				userBalance.addCurrency(currency);
//			}
//		}
//		
//		map.put(HttpResultMap.IS_OK, true);
//		map.put(HttpResultMap.DATA, userBalance);
//		return map;
//	}

	@POST
	@Path("/walletAddressInfo")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> walletAddressInfo(
			@FormParam("walletAddress") String walletAddress) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		RippleAddressPublicInformation pubInfo = 
				rippleQueryService.getAccountInfo(walletAddress);
		
		TrustLines trustLines = 
				rippleQueryService.getTrustLines(walletAddress);
		UserBalance userBalance = new UserBalance();
		userBalance.addCurrency(new UserCurrency(pubInfo.xrpBalance.doubleValue()));
		
		for(TrustLine trustLine : trustLines) {
			UserCurrency currency = 
				new UserCurrency(
						Double.parseDouble(trustLine.balance),
						trustLine.currency,
						trustLine.otherAccount);
			userBalance.addCurrency(currency);
		}
		
		map.put(HttpResultMap.IS_OK, true);
		map.put(HttpResultMap.DATA, userBalance);
		return map;
	}
	
	@POST
	@Path("/deposit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deposit(
			@FormParam("userId") String userId,
			@FormParam("currency") String currency,
			@FormParam("issuer") String issuer,
			@FormParam("walletAddress") String walletAddress,
			@FormParam("amount") String amount,
			@FormParam("fee") String fee,
			@FormParam("paymentId") String paymentId) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		logger.debug("userId = " + userId + ", amount = " + amount + ", fee = " + fee + ", paymentId = " + paymentId);
		
		User user = userService.getUser(userId);
		if(user == null) {
			map.put(HttpResultMap.IS_OK, false);
			return map;
		}
		
		DepositRecord depositRecord = null;
		if((depositRecord = depositRecordService.createDepositRecord(
				userId, 
				walletAddress,
				Double.parseDouble(amount), 
				currency,
				issuer,
				Double.parseDouble(fee), 
				paymentId)) == null) 
		{
			map.put(HttpResultMap.IS_OK, false);
			return map;
		}
		
		mailService.instructDeposit(depositRecord, user.getEmail());
		
		map.put(HttpResultMap.IS_OK, true);
		return map;
	}
	
	@POST
	@Path("/sendIOU")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> sendIOU(
			@FormParam("paymentId") String paymentId) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		DepositRecord depositRecord = 
				depositRecordService.getDepositRecord(paymentId); 
		
		if(depositRecord == null) {
			map.put(HttpResultMap.IS_OK, false);
			return map;
		}
		
		User user = userService.getUser(depositRecord.getUserId());
		
		String gatewayCurrency = configService.getSettingValue(Settings.CURRENCY);
		String gatewayHotWalletAddress = 
				configService.getSettingValue(
						Settings.GATEWAY_HOT_WALLET_ADDRESS);
		
		wallet.sendIOU(
				gatewayCurrency,
				depositRecord.getPaid().doubleValue(), 
				gatewayHotWalletAddress,
				user.getRippleAddress(), 
				1L);
		
		map.put(HttpResultMap.IS_OK, true);
		return map;
	}
	
	
	@POST
	@Path("/withdraw")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> withdraw(
			@FormParam("userId") String userId,
			@FormParam("walletAddress") String walletAddress,
			@FormParam("destinationTag") long destinationTag,
			@FormParam("amount") String amount,
			@FormParam("fee") String fee
			) 
	{	
		Map<String, Object> map = new HashMap<String, Object>();
		
		logger.debug("userId = " + userId + ", destinationTag " + destinationTag + ", amount = " + amount + ", fee = " + fee);
		
		User user = userService.getUser(userId);
		if(user == null) {
			map.put(HttpResultMap.IS_OK, false);
			map.put(HttpResultMap.MESSAGE, "can't find user, " + userId);
			return map;
		}
		
		List<GatewayCurrency> gatewayCurrencyList = gatewayService.getGatewayCurrencyList();
		String gatewayHotWalletAddress = 
				configService.getSettingValue(
						Settings.GATEWAY_HOT_WALLET_ADDRESS);
		String gatewayWithdrawFee = configService.getSettingValue(Settings.DESPOIT_FEE);
		
		List<RippleTransactionResult> txResultList =
				rippleQueryService.getAccountTx(walletAddress, 10);
		boolean found = false;
		DenominatedIssuedCurrency realAmount = null;
		for(RippleTransactionResult txResult: txResultList) {
			if(txResult.getTx() instanceof RippleTxPaymentResult) {
				RippleTxPaymentResult paymentResult = 
						(RippleTxPaymentResult)txResult.getTx();
				long tag = paymentResult.getDestinationTag();
				String destinationAddress = paymentResult.getDestination();
				if(tag > 0 && tag == destinationTag && 
					destinationAddress.equals(gatewayHotWalletAddress)) 
				{
					found = true;
					realAmount = paymentResult.getAmount();
					break;
				}
			}
		}
		if(!found) {
			map.put(HttpResultMap.IS_OK, false);
			map.put(HttpResultMap.MESSAGE, "can't find this withdraw request !" );
			return map;
		}
		
		boolean match = false;
		for(int i = 0; i < gatewayCurrencyList.size(); i++) {
			String gatewayCurrency = gatewayCurrencyList.get(i).getName();
			String gatewayCurrencyIssuer = gatewayCurrencyList.get(i).getIssuer();
			if(gatewayCurrency.equals(realAmount.getCurrency()) &&
				gatewayCurrencyIssuer.equals(realAmount.getIssuer().toString()))
			{
				match = true;
			}
		}
		
		if(!match) {
			map.put(HttpResultMap.IS_OK, false);
			map.put(HttpResultMap.MESSAGE, "you are not send IOU supported by gateway, please contact gateway operator to handle it !");
			return map;
		}
		
		BigDecimal gatewayFee = 
				realAmount.getAmount().multiply(
					new BigDecimal(Double.parseDouble(gatewayWithdrawFee)));
		
		if(withdrawRecordService.createWithdraw(
				userId, 
				walletAddress, 
				realAmount.getAmount().doubleValue(), 
				realAmount.getCurrency(),
				realAmount.getIssuer().toString(),
				Double.parseDouble(fee), 
				null, null, null, ""+destinationTag) == null) 
		{
			map.put(HttpResultMap.IS_OK, false);
			return map;
		}
		
		try{
			if(subledger == null) {
				subledger = new FederationSubledger();
			}
			subledger.userWithdrawFund(
					IDGenerator.getId(), 
					user.getEmail(), 
					realAmount.getAmount().toString(), 
					realAmount.getAmount().subtract(gatewayFee).toString(), 
					gatewayFee.toString());
		
		}catch(Exception e) {
			e.printStackTrace();
			map.put(HttpResultMap.IS_OK, false);
			map.put(HttpResultMap.MESSAGE, e.getMessage());
			return map;
		}
		
		map.put(HttpResultMap.IS_OK, true);
		return map;
	}
}