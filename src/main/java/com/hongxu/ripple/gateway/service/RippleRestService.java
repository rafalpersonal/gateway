package com.hongxu.ripple.gateway.service;

import java.math.BigDecimal;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.utils.HttpUtils;
import com.hongxu.ripple.gateway.vo.HttpResult;
import com.hongxu.ripple.gateway.vo.ripple.Payment;
import com.hongxu.ripple.gateway.vo.ripple.PreparingResponse;
import com.hongxu.ripple.gateway.vo.ripple.SubmittingRequest;
import com.hongxu.ripple.gateway.vo.ripple.SubmittingResponse;
import com.hongxu.ripplermb.domain.core.support.PaymentStatus;

@Service
public class RippleRestService {
	
	private Logger logger = LoggerFactory.getLogger(RippleRestService.class);
	
	private HttpUtils httpUtils = new HttpUtils();
	
	private ObjectMapper om = new ObjectMapper();
	

	//private String RIPPLE_REST_SERVER_URL = "https://192.168.92.129:5990/api/v1/";
	private String RIPPLE_REST_SERVER_URL = "https://54.201.224.87:5990/api/v1/";
	
	public Payment preparingPayment(String srcAddress,String dstAddress,BigDecimal amount,String currency) {
		String method = "addresses/";
		String url = RIPPLE_REST_SERVER_URL + method + srcAddress + "/payments/" + dstAddress + "/" + amount.doubleValue() + "+" + currency;
		Payment payment = null;
		try{
			logger.debug("preparing payment url:" + url);
			HttpResult result = httpUtils.get(url);
			logger.debug("preparing payment result:" + result);
			if(result.getStatusCode() == 200){
				PreparingResponse response = om.readValue(result.getReturnData(), PreparingResponse.class);
				if(response.success){
					if(response.payments != null && response.payments.size() > 0) payment = response.payments.get(0);
				}
			}
		}catch(Exception ex){
			logger.error("preparing payment error.", ex);
		}
		return payment;
	}
	
	public SubmittingResponse submittingPayment(String secret,String srcAddress,Payment payment) {
		String method = "addresses/";
		String url = RIPPLE_REST_SERVER_URL + method + srcAddress + "/payments";
		SubmittingResponse response = null;
		SubmittingRequest request = new SubmittingRequest();
		request.secret = secret;
		request.payment = payment;
		try{
			logger.debug("submitting payment url:" + url);
			HttpResult result = httpUtils.postJson(url, om.writeValueAsString(request));
			logger.debug("submitting payment result:" + result);
			if(result.getStatusCode() == 200){
				response = om.readValue(result.getReturnData(), SubmittingResponse.class);
			}
		}catch(Exception ex){
			logger.error("submitting payment error.", ex);
		}
		return response;
	}
	
	public void confirmingPayment() {
		
	}
}
