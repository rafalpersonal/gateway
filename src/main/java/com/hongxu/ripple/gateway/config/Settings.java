package com.hongxu.ripple.gateway.config;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

public class Settings {
	
	public static final String GATEWAY_NAME = "gateway_name";
	public static final String GATEWAY_ADDRESS = "gateway_address";
	public static final String GATEWAY_BANK_NAME = "bank_name";
	public static final String GATEWAY_BANK_ROUTING_NUMBER = "bank_routing_number";
	public static final String GATEWAY_BANK_ACCOUNT_NUMBER = "bank_account_number";
	
	public static final String GATEWAY_HOT_WALLET_NAME = "gateway_hot_wallet_name";
	public static final String GATEWAY_HOT_WALLET_ADDRESS = "gateway_hot_wallet_address";
	public static final String GATEWAY_COLD_WALLET_ADDRESS = "gateway_cold_wallet_address";
	public static final String GATEWAY_HOT_WALLET_PASSWORD = "gateway_hot_wallet_password";
	
	public static final String SMTP_SERVER = "smtp";
	public static final String SMTP_USERNAME = "smtp_username";
	public static final String SMTP_PASSWORD = "smtp_password";
	
	
	public static final String LOGO = "logo";
	public static final String SKINS = "skins";
	public static final String LANGUAGE = "language";
	public static final String CURRENCY = "currency";
	public static final String PAYMENT_FEE = "payment_fee";
	public static final String DESPOIT_FEE = "deposit_fee";
	public static final String WITHDRAW_FEE = "withdraw_fee";
	public static final String ROOT_URL = "root_url";
}
