package com.hongxu.ripple.gateway.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 王欣
 * @Version v1.0
 * @Date 2013-11-28 下午6:10:22
 */

public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String currency = null;
	private double amount = 0;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
