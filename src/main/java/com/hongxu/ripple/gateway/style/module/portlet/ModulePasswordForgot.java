package com.hongxu.ripple.gateway.style.module.portlet;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModulePasswordForgot extends Portlet {

	public ModulePasswordForgot(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String title = null;
		private String content = null;
		private String emailPlaceHolder = null;
		private String submitButtonLabel = null;
		
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
		public String getEmailPlaceHolder() {
			return emailPlaceHolder;
		}
		public void setEmailPlaceHolder(String emailPlaceHolder) {
			this.emailPlaceHolder = emailPlaceHolder;
		}
		public String getSubmitButtonLabel() {
			return submitButtonLabel;
		}
		public void setSubmitButtonLabel(String submitButtonLabel) {
			this.submitButtonLabel = submitButtonLabel;
		}
	}
}
