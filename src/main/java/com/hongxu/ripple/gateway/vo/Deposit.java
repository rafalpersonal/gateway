/**
 * @Title Payment.java
 * @Package com.hongxu.ripple.gateway.vo
 * @Project ripple-gateway
 * @Author 王欣
 * @Version v1.0
 * @Date 2014-1-21 下午4:19:56
 */
package com.hongxu.ripple.gateway.vo;

import java.io.Serializable;

import com.hongxu.ripplermb.domain.core.po.DepositRecord;
import com.hongxu.ripplermb.domain.core.po.SysPayment;
import com.hongxu.ripplermb.domain.core.po.User;

/**
 * @author 王欣
 * @Version v1.0
 * @Date 2014-1-21 下午4:19:56
 */
public class Deposit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private User user;
	private SysPayment sysPayment;
	private DepositRecord depositRecord;
	
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
	public DepositRecord getDepositRecord() {
		return depositRecord;
	}
	public void setDepositRecord(DepositRecord depositRecord) {
		this.depositRecord = depositRecord;
	}
	

}
