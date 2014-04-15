package com.hongxu.ripple.gateway.style.module.sidebar;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleSidebarLogin extends Sidebar {
	
	public ModuleSidebarLogin(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String moduleTitle = null;
		private String usernamePlaceHolder = null;
		private String passwordPlaceHolder = null;
		private String registerLink = null;
		private String forgotPasswordLink = null;
		private String submitTitle = null;
		
		public String getModuleTitle() {
			return moduleTitle;
		}
		public void setModuleTitle(String moduleTitle) {
			this.moduleTitle = moduleTitle;
		}
		public String getUsernamePlaceHolder() {
			return usernamePlaceHolder;
		}
		public void setUsernamePlaceHolder(String usernamePlaceHolder) {
			this.usernamePlaceHolder = usernamePlaceHolder;
		}
		public String getPasswordPlaceHolder() {
			return passwordPlaceHolder;
		}
		public void setPasswordPlaceHolder(String passwordPlaceHolder) {
			this.passwordPlaceHolder = passwordPlaceHolder;
		}
		public String getRegisterLink() {
			return registerLink;
		}
		public void setRegisterLink(String registerLink) {
			this.registerLink = registerLink;
		}
		public String getForgotPasswordLink() {
			return forgotPasswordLink;
		}
		public void setForgotPasswordLink(String forgotPasswordLink) {
			this.forgotPasswordLink = forgotPasswordLink;
		}
		public String getSubmitTitle() {
			return submitTitle;
		}
		public void setSubmitTitle(String submitTitle) {
			this.submitTitle = submitTitle;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		ModuleSidebarLogin.VO vo = new ModuleSidebarLogin.VO();
		vo.setModuleTitle("Login");
		vo.setUsernamePlaceHolder("Username");
		vo.setPasswordPlaceHolder("Password");
		vo.setRegisterLink("Sign Up");
		vo.setForgotPasswordLink("Forgot Password");
		vo.setSubmitTitle("Sign in");
		
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(vo));
	}
}
