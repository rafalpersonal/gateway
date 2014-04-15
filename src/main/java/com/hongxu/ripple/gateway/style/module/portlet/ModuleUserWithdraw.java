package com.hongxu.ripple.gateway.style.module.portlet;

import org.codehaus.jackson.map.ObjectMapper;

import com.hongxu.ripple.gateway.style.module.Module;
import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleUserWithdraw extends Portlet {

	private String withdrawFee = null;

	public String getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(String withdrawFee) {
		this.withdrawFee = withdrawFee;
	}
	
	public ModuleUserWithdraw(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String title = null;
		private String content = null;
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
	}
}
