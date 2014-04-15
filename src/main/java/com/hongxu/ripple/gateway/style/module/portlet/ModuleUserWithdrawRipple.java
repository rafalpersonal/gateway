package com.hongxu.ripple.gateway.style.module.portlet;

import java.util.ArrayList;
import java.util.List;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserWallet;

public class ModuleUserWithdrawRipple extends Portlet {

	private String withdrawFee = null;
	private User user = null;
	private List<UserWallet> userWalletList = new ArrayList<UserWallet>();
	private String gatewayRippleAddress = null;
	private List<GatewayCurrency> gatewayCurrencyList =
			new ArrayList<GatewayCurrency>();

	public String getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(String withdrawFee) {
		this.withdrawFee = withdrawFee;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public List<UserWallet> getUserWalletList() {
		return userWalletList;
	}

	public void setUserWalletList(List<UserWallet> userWalletList) {
		this.userWalletList = userWalletList;
	}

	public String getGatewayRippleAddress() {
		return gatewayRippleAddress;
	}

	public void setGatewayRippleAddress(String gatewayRippleAddress) {
		this.gatewayRippleAddress = gatewayRippleAddress;
	}
	
	public List<GatewayCurrency> getGatewayCurrencyList() {
		return gatewayCurrencyList;
	}

	public void setGatewayCurrencyList(List<GatewayCurrency> gatewayCurrencyList) {
		this.gatewayCurrencyList = gatewayCurrencyList;
	}
	
	public ModuleUserWithdrawRipple(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String withdrawCurrencyLabel = null;
		private String amountLabel = null;
		private String selectWalletLabel = null;
		private String withdrawButtonLabel = null;
		private String notifyGatewayButtonLabel = null;
		private String feeLabel = null;
		private String totalLabel = null;
		
		
		public String getWithdrawCurrencyLabel() {
			return withdrawCurrencyLabel;
		}
		public void setWithdrawCurrencyLabel(String withdrawCurrencyLabel) {
			this.withdrawCurrencyLabel = withdrawCurrencyLabel;
		}
		public String getAmountLabel() {
			return amountLabel;
		}
		public void setAmountLabel(String amountLabel) {
			this.amountLabel = amountLabel;
		}
		public String getSelectWalletLabel() {
			return selectWalletLabel;
		}
		public void setSelectWalletLabel(String selectWalletLabel) {
			this.selectWalletLabel = selectWalletLabel;
		}
		public String getWithdrawButtonLabel() {
			return withdrawButtonLabel;
		}
		public void setWithdrawButtonLabel(String withdrawButtonLabel) {
			this.withdrawButtonLabel = withdrawButtonLabel;
		}
		public String getNotifyGatewayButtonLabel() {
			return notifyGatewayButtonLabel;
		}
		public void setNotifyGatewayButtonLabel(String notifyGatewayButtonLabel) {
			this.notifyGatewayButtonLabel = notifyGatewayButtonLabel;
		}
		public String getFeeLabel() {
			return feeLabel;
		}
		public void setFeeLabel(String feeLabel) {
			this.feeLabel = feeLabel;
		}
		public String getTotalLabel() {
			return totalLabel;
		}
		public void setTotalLabel(String totalLabel) {
			this.totalLabel = totalLabel;
		}
		
	}
}
