package com.hongxu.ripple.gateway.style.module.portlet;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.Organization;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleRegister extends Portlet {

	private Organization org = null;
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public ModuleRegister(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String moduleTitle = null;
		private String rippleAddressTitle = null;
		private String createRippleWalletLink = null;
		private String emailTitle = null;
		private String passwordTitle = null;
		private String confirmPasswordTitle = null;
		private String agreeCheckBoxTitle = null;
		private String termsOfServiceTitle = null;
		private String submitTitle = null;
		
		public String getModuleTitle() {
			return moduleTitle;
		}
		public void setModuleTitle(String moduleTitle) {
			this.moduleTitle = moduleTitle;
		}
		public String getRippleAddressTitle() {
			return rippleAddressTitle;
		}
		public void setRippleAddressTitle(String rippleAddressTitle) {
			this.rippleAddressTitle = rippleAddressTitle;
		}
		public String getCreateRippleWalletLink() {
			return createRippleWalletLink;
		}
		public void setCreateRippleWalletLink(String createRippleWalletLink) {
			this.createRippleWalletLink = createRippleWalletLink;
		}
		public String getEmailTitle() {
			return emailTitle;
		}
		public void setEmailTitle(String emailTitle) {
			this.emailTitle = emailTitle;
		}
		public String getPasswordTitle() {
			return passwordTitle;
		}
		public void setPasswordTitle(String passwordTitle) {
			this.passwordTitle = passwordTitle;
		}
		public String getConfirmPasswordTitle() {
			return confirmPasswordTitle;
		}
		public void setConfirmPasswordTitle(String confirmPasswordTitle) {
			this.confirmPasswordTitle = confirmPasswordTitle;
		}
		public String getAgreeCheckBoxTitle() {
			return agreeCheckBoxTitle;
		}
		public void setAgreeCheckBoxTitle(String agreeCheckBoxTitle) {
			this.agreeCheckBoxTitle = agreeCheckBoxTitle;
		}
		public String getTermsOfServiceTitle() {
			return termsOfServiceTitle;
		}
		public void setTermsOfServiceTitle(String termsOfServiceTitle) {
			this.termsOfServiceTitle = termsOfServiceTitle;
		}
		public String getSubmitTitle() {
			return submitTitle;
		}
		public void setSubmitTitle(String submitTitle) {
			this.submitTitle = submitTitle;
		}
	}

	public static void main(String[] args) throws Exception {
		
		ModuleRegister.VO vo = new ModuleRegister.VO();
		vo.setModuleTitle("Login");
		vo.setRippleAddressTitle("Ripple Wallet Public Address ");
		vo.setCreateRippleWalletLink("Create Ripple Wallet");
		vo.setEmailTitle("Email");
		vo.setPasswordTitle("Password");
		vo.setConfirmPasswordTitle("Confirm Password");
		vo.setAgreeCheckBoxTitle("我同意遵守");
		vo.setTermsOfServiceTitle("的服务条款");
		vo.setSubmitTitle("Sign in");
		
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(vo));
	}
}
