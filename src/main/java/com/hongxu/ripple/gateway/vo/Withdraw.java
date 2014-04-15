/**
 * @Title Withdraw.java
 * @Package com.hongxu.ripple.gateway.vo
 * @Project ripple-gateway
 * @Author 王欣
 * @Version v1.0
 * @Date 2014-1-21 下午4:19:56
 */
package com.hongxu.ripple.gateway.vo;

import java.io.Serializable;

import com.hongxu.ripplermb.domain.core.po.SysPayment;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.WithdrawRecord;

/**
 * @author 王欣
 * @Version v1.0
 * @Date 2014-1-21 下午4:19:56
 */
public class Withdraw implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private User user;
	private SysPayment sysPayment;
	private WithdrawRecord withdrawRecord;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SysPayment getSysPayment() {
		return sysPayment;
	}
	public void setSysPayment(SysPayment sysPayment) {
		this.sysPayment = sysPayment;
	}
	/**
	 * @return the withdrawRecord
	 */
	public WithdrawRecord getWithdrawRecord() {
		return withdrawRecord;
	}
	/**
	 * @param withdrawRecord the withdrawRecord to set
	 */
	public void setWithdrawRecord(WithdrawRecord withdrawRecord) {
		this.withdrawRecord = withdrawRecord;
	}

}
