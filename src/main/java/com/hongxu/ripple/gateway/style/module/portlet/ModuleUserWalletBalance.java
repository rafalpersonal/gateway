package com.hongxu.ripple.gateway.style.module.portlet;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;

public class ModuleUserWalletBalance extends Portlet {

	private String walletAddress = null;
	private User user = null;
	
	
	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ModuleUserWalletBalance(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		
	}
}
