package com.hongxu.ripple.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.SysPaymentMapper;
import com.hongxu.ripplermb.domain.core.po.SysPayment;
import com.hongxu.ripplermb.domain.core.po.SysPaymentCriteria;

@Service
public class SysPaymentService {
	
	@Resource
	private SysPaymentMapper sysPaymentMapper;

	public SysPayment getSysPayment(String paymentId) {
		
		return sysPaymentMapper.selectByPrimaryKey(paymentId);
	}
	
	public List<SysPayment> findSysPaymentList(){
		SysPaymentCriteria c = new SysPaymentCriteria();
		c.setOrderByClause("id ASC");
		return sysPaymentMapper.selectByExample(c);
	}
}
