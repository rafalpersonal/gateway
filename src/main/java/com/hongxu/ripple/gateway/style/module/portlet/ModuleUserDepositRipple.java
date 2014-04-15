package com.hongxu.ripple.gateway.style.module.portlet;

import java.util.ArrayList;
import java.util.List;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.SysPayment;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.po.UserWallet;

public class ModuleUserDepositRipple extends Portlet {

	private String depositFee = null;
	private User user = null;
	private List<UserWallet> userWalletList = new ArrayList<UserWallet>();
	private String gatewayRippleAddress = null;
	private List<GatewayCurrency> gatewayCurrencyList =
				new ArrayList<GatewayCurrency>();
	private List<SysPayment> paymentList = null;
	
	public String getDepositFee() {
		return depositFee;
	}
	public void setDepositFee(String depositFee) {
		this.depositFee = depositFee;
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
	public List<SysPayment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<SysPayment> paymentList) {
		this.paymentList = paymentList;
	}

	public ModuleUserDepositRipple(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String depositCurrencyLabel = null;
		private String selectWalletLabel = null;
		private String amountLabel = null;
		private String accountLabel = null;
		private String depositButtonLabel = null;
		private String setTrustLineButtonLabel = null;
		private String feeLabel = null;
		private String totalLabel = null;
		
		public String getDepositCurrencyLabel() {
			return depositCurrencyLabel;
		}
		public void setDepositCurrencyLabel(String depositCurrencyLabel) {
			this.depositCurrencyLabel = depositCurrencyLabel;
		}
		public String getSelectWalletLabel() {
			return selectWalletLabel;
		}
		public void setSelectWalletLabel(String selectWalletLabel) {
			this.selectWalletLabel = selectWalletLabel;
		}
		public String getAmountLabel() {
			return amountLabel;
		}
		public void setAmountLabel(String amountLabel) {
			this.amountLabel = amountLabel;
		}
		public String getAccountLabel() {
			return accountLabel;
		}
		public void setAccountLabel(String accountLabel) {
			this.accountLabel = accountLabel;
		}
		public String getDepositButtonLabel() {
			return depositButtonLabel;
		}
		public void setDepositButtonLabel(String depositButtonLabel) {
			this.depositButtonLabel = depositButtonLabel;
		}
		
		public String getSetTrustLineButtonLabel() {
			return setTrustLineButtonLabel;
		}
		public void setSetTrustLineButtonLabel(String setTrustLineButtonLabel) {
			this.setTrustLineButtonLabel = setTrustLineButtonLabel;
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
