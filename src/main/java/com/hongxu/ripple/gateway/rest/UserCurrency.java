package com.hongxu.ripple.gateway.rest;

import java.math.BigDecimal;

public class UserCurrency {
	private String currency = null;
	private String issuer = null;
	private double amount = 0.0;
	
	public UserCurrency(double amount) {
		this(amount, "xrp", null);
	}
	
	public UserCurrency(double amount, String currency, String issuer) {
		this.currency = currency;
		this.amount = amount;
		this.issuer = issuer;
	}
	
	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
