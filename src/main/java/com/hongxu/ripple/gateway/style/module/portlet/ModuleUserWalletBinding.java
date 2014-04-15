package com.hongxu.ripple.gateway.style.module.portlet;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysPayment;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;

public class ModuleUserWalletBinding extends Portlet {

	private User user = null;
	
	public ModuleUserWalletBinding(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	
	
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public static class VO extends ModuleVO {
		
		private String walletNameLabel = null;
		private String walletAddressLabel = null;
		private String bindingButtonLabel = null;
		
		public String getWalletNameLabel() {
			return walletNameLabel;
		}
		public void setWalletNameLabel(String walletNameLabel) {
			this.walletNameLabel = walletNameLabel;
		}
		public String getWalletAddressLabel() {
			return walletAddressLabel;
		}
		public void setWalletAddressLabel(String walletAddressLabel) {
			this.walletAddressLabel = walletAddressLabel;
		}
		public String getBindingButtonLabel() {
			return bindingButtonLabel;
		}
		public void setBindingButtonLabel(String bindingButtonLabel) {
			this.bindingButtonLabel = bindingButtonLabel;
		}
	}
}
