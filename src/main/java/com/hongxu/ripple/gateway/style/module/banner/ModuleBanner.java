package com.hongxu.ripple.gateway.style.module.banner;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class ModuleBanner extends Banner {

	public ModuleBanner(SysTemplate sysTemplate, ModuleVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends ModuleVO {
		
		private String backgroundImage = null;

		public String getBackgroundImage() {
			return backgroundImage;
		}

		public void setBackgroundImage(String backgroundImage) {
			this.backgroundImage = backgroundImage;
		}
		
	}
}
