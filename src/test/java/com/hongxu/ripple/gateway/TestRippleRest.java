package com.hongxu.ripple.gateway;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hongxu.ripple.gateway.AbstractTest;
import com.hongxu.ripple.gateway.service.RippleRestService;
import com.hongxu.ripple.gateway.vo.ripple.Payment;
import com.hongxu.ripple.gateway.vo.ripple.SubmittingResponse;

/**
 * 
 * @author 王欣
 * @version v1.0
 * @copy wangxin
 * 2012-12-17 下午4:57:30
 */
public class TestRippleRest extends AbstractTest {
	private Logger logger = LoggerFactory.getLogger(TestRippleRest.class);
	
	@Resource 
	private RippleRestService rippleRestService;
	@Test
	public void testPayment(){
		String secret = "snAEhDZ1knHE4dajGCLBneA9PcswW";
		String srcAddress = "r3drNuNfmgMA9AtdVEunX4Cxyfr9kSgC6d";
		String dstAddress = "r3jjRU5BGWQgnTdY5xXqTYepmFksnwD7VG";
		BigDecimal amount = new BigDecimal(0.01);
		String currency = "XRP";
		Payment payment = rippleRestService.preparingPayment(srcAddress, dstAddress, amount, currency);
		if(payment != null){
			logger.debug("preparing a payment result:" + payment);
			SubmittingResponse response = rippleRestService.submittingPayment(secret, dstAddress, payment);
			if(response != null){
				logger.debug("submitting a payment result:" + response);
			}
		}
	}
	

}
