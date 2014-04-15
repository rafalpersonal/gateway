package com.hongxu.ripple.gateway.style.module.portlet;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModulePasswordReset extends Portlet {

	private String email;
	private String verifiedCode;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerifiedCode() {
		return verifiedCode;
	}

	public void setVerifiedCode(String verifiedCode) {
		this.verifiedCode = verifiedCode;
	}

	public ModulePasswordReset(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String title = null;
		private String content = null;
		private String passwordPlaceHolder = null;
		private String verifiedPasswordPlaceHolder = null;
		private String resetPasswordButtonLabel = null;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getPasswordPlaceHolder() {
			return passwordPlaceHolder;
		}
		public void setPasswordPlaceHolder(String passwordPlaceHolder) {
			this.passwordPlaceHolder = passwordPlaceHolder;
		}
		public String getVerifiedPasswordPlaceHolder() {
			return verifiedPasswordPlaceHolder;
		}
		public void setVerifiedPasswordPlaceHolder(String verifiedPasswordPlaceHolder) {
			this.verifiedPasswordPlaceHolder = verifiedPasswordPlaceHolder;
		}
		public String getResetPasswordButtonLabel() {
			return resetPasswordButtonLabel;
		}
		public void setResetPasswordButtonLabel(String resetPasswordButtonLabel) {
			this.resetPasswordButtonLabel = resetPasswordButtonLabel;
		}
	}
}
