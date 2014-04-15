package com.hongxu.ripple.gateway.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jrippleapi.connection.DenominatedIssuedCurrency;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.GatewayCurrencyMapper;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrencyCriteria;

@Service(value="gatewayService")
public class GatewayService {
	
	@Resource
	private GatewayCurrencyMapper gatewayCurrencyMapper;
		
	public List<GatewayCurrency> getGatewayCurrencyList() {
		
		try{
			GatewayCurrencyCriteria criteria = new GatewayCurrencyCriteria();
			
			List<GatewayCurrency> currencyList = 
					gatewayCurrencyMapper.selectByExample(criteria);
			
			return currencyList;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
