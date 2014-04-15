package com.hongxu.ripple.gateway.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hongxu.ripple.gateway.style.skin.PageSkin;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.Organization;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserRealInfo;
import com.hongxu.ripplermb.domain.core.po.UserWallet;

public class SessionBundle {

	private final static String SESSION_ORGANIZATION = "organization";
	private final static String SESSION_PAGE_SKIN = "page_skin";
	private final static String SESSION_USER = "user";
	private final static String SESSION_USER_INFO = "user_info";
	private static final String SESSION_USER_WALLET_LIST = "user_wallet_list";
	private static final String SESSION_GATEWAY_CURRENCY_LIST = "gateway_currency_list";
	
	
	public static Organization getOrganization(HttpSession session) {
		
		Organization org = (Organization)session.getAttribute(SESSION_ORGANIZATION);
		return org;
	}
	
	public static void setOrganization(HttpSession session, Organization org) {
		session.setAttribute(SESSION_ORGANIZATION, org);
	}
	
	public static PageSkin getPageSkin(HttpSession session) {
		
		PageSkin pageSkin = (PageSkin)session.getAttribute(SESSION_PAGE_SKIN);
		return pageSkin;
	}
	
	public static void setPageSkin(HttpSession session, PageSkin pageSkin) {
		session.setAttribute(SESSION_PAGE_SKIN, pageSkin);
	}
	
	public static User getUser(HttpSession session) {
		
		User user = (User)session.getAttribute(SESSION_USER);
		return user;
	}
	
	public static void setUser(HttpSession session, User user) {
		session.setAttribute(SESSION_USER, user);
	}
	
	public static UserRealInfo getUserRealInfo(HttpSession session) {
		
		UserRealInfo userInfo = (UserRealInfo)session.getAttribute(SESSION_USER_INFO);
		return userInfo;
	}
	
	public static void setUserRealInfo(HttpSession session, UserRealInfo userRealInfo) {
		session.setAttribute(SESSION_USER_INFO, userRealInfo);
	}
	
	public static List<UserWallet> getUserWalletList(HttpSession session) {
		
		List<UserWallet> userWalletList = (List<UserWallet>)session.getAttribute(SESSION_USER_WALLET_LIST);
		return userWalletList;
	}
	
	public static void setUserWalletList(HttpSession session, List<UserWallet> userWalletList) {
		session.setAttribute(SESSION_USER_WALLET_LIST, userWalletList);
	}
	
	public static List<GatewayCurrency> getGatewayCurrencyList(HttpSession session) {
		
		List<GatewayCurrency> currencyList = (List<GatewayCurrency>)
				session.getAttribute(SESSION_GATEWAY_CURRENCY_LIST);
		return currencyList;
	}
	
	public static void setGatewayCurrencyList(HttpSession session, List<GatewayCurrency> currencyList) {
		session.setAttribute(SESSION_GATEWAY_CURRENCY_LIST, currencyList);
	}
}
