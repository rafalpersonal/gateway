package com.hongxu.ripple.gateway.rest;

import java.util.ArrayList;
import java.util.List;

import jrippleapi.connection.DenominatedIssuedCurrency;

import com.hongxu.ripplermb.domain.core.po.User;

public class UserBalance {
	
	private List<UserCurrency> currencyList = new ArrayList<UserCurrency>();

	public List<UserCurrency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<UserCurrency> currencyList) {
		this.currencyList = currencyList;
	}

	public void addCurrency(UserCurrency currency) {
		this.currencyList.add(currency);
	}

}

