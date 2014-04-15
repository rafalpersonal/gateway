package com.hongxu.ripple.gateway.style;

public class WebsiteConfig {
	
	public static final String TEMPLATE_ROOT = "template";
	
	public static class PageUrl { 
	
		public static final String INDEX_URL = "index.html";
		public static final String USER_LOGIN_URL = "user/login.html";
		public static final String USER_ERGISTER_URL = "user/register.html";
		public static final String USER_REGISTER_FINISHED_URL = "message/register_finished.html";
		public static final String PASSWORD_FORGOT_URL =  "user/password_forgot.html";
		public static final String PASSWORD_FORGOT_FINISHED_URL = "message/password_forgot_finished.html";
		public static final String USER_CENTER_URL = "user_center/account.html";
		
	}
	
	public static class PageKeyForSkin {
		
		public static final String DEFAULT_PAGE = "Default";
		public static final String MAIN_PAGE = "MainPage";
		public static final String USER_CENTER_PAGE = "UserCenter";
	}
	
	/*
	 * used for query from busi_page_data
	 */
	public static class PageKeyForDisplay {		
		
		public static final String PAGE_INDEX = "page_index";
		public static final String PAGE_REGISTER = "page_index";
		public static final String PAGE_LOGIN = "page_index";
		public static final String PAGE_USER_ACCOUNT_PROFILE = "page_user_account_verify";
		public static final String PAGE_USER_DEPOSIT = "page_user_deposit";
		public static final String PAGE_USER_DEPOSIT_RIPPLE = "page_user_deposit_ripple";
		public static final String PAGE_USER_WALLET = "page_user_wallet";
		public static final String PAGE_USER_WALLET_BINDING = "page_user_wallet_binding";
		public static final String PAGE_USER_WALLET_BALANCE = "page_user_wallet_balance";
		public static final String PAGE_USER_WITHDRAW = "page_user_withdraw";
		public static final String PAGE_USER_WITHDRAW_RIPPLE = "page_user_withdraw_ripple";
	}
	
	public static class ModuleKeyForDisplay {
		
		public static final String MODULE_HEADER = "module_header";
		public static final String MODULE_NAVBAR = "module_navbar";
		public static final String MODULE_FOOTER = "module_footer";
		public static final String MODULE_BANNER = "module_banner";
		public static final String MODULE_LOGIN = "module_login";
		public static final String MODULE_PORTLET_REGISTER = "module_register";
		public static final String MODULE_PASSWORD_FORGOT = "module_password_forgot";
		public static final String MODULE_PASSWORD_RESET = "module_password_reset";
		// account
		public static final String MODULE_SIDEBAR_USER_ACCOUNT = "module_user_sideBar_account";
		public static final String MODULE_PORTLET_USER_ACCOUNT_BALANCE = "module_user_account_balance";
		public static final String MODULE_PORTLET_USER_PROFILE = "module_profile";
		// wallet
		public static final String MODULE_SIDEBAR_USER_WALLET = "module_sidebar_user_wallet";
		public static final String MODULE_PORTLET_USER_WALLET = "module_portlet_user_wallet";
		public static final String MODULE_PORTLET_USER_WALLET_BINDING = "module_portlet_user_wallet_binding";
		// deposit
		public static final String MODULE_SIDEBAR_USER_DEPOSIT = "module_user_sideBar_deposit";
		public static final String MODULE_PORTLET_USER_DEPOSIT = "module_user_deposit";
		public static final String MODULE_PORTLET_USER_DEPOSIT_RIPPLE = "module_user_deposit_ripple";
		// withdraw
		public static final String MODULE_SIDEBAR_USER_WITHDRAW = "module_user_sideBar_withdraw";
		public static final String MODULE_PORTLET_USER_WITHDRAW = "module_user_withdraw";
		public static final String MODULE_PORTLET_USER_WITHDRAW_RIPPLE = "module_user_withdraw_ripple";
		
	}
}
