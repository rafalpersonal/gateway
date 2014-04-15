package com.hongxu.ripple.gateway.style.module.portlet;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleUserDeposit extends Portlet {

	private String depositFee = null;
	
	public String getDepositFee() {
		return depositFee;
	}

	public void setDepositFee(String depositFee) {
		this.depositFee = depositFee;
	}

	public ModuleUserDeposit(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String amountLabel = null;
		private String accountLabel = null;
		private String getInvoiceButtonLabel = null;
		private String feeLabel = null;
		private String totalLabel = null;
		
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
		public String getGetInvoiceButtonLabel() {
			return getInvoiceButtonLabel;
		}
		public void setGetInvoiceButtonLabel(String getInvoiceButtonLabel) {
			this.getInvoiceButtonLabel = getInvoiceButtonLabel;
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

	public static void main(String[] args) throws Exception {
		
		ModuleUserDeposit.VO vo = new ModuleUserDeposit.VO();
		vo.setAmountLabel("Amount");
		vo.setAccountLabel("bank account");
		vo.setGetInvoiceButtonLabel("Get Invoice");
		vo.setFeeLabel("Fee");
		vo.setTotalLabel("Total");
		
		ObjectMapper om = new ObjectMapper();
		System.out.print(om.writeValueAsString(vo));
	}
}
