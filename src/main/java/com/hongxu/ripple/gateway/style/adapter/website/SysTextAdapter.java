package com.hongxu.ripple.gateway.style.adapter.website;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hongxu.ripple.gateway.service.ConfigService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.adapter.Adapter;
import com.hongxu.ripple.gateway.style.page.PageSysText;
import com.hongxu.ripple.gateway.style.page.PageSysTextVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

@Service
public class SysTextAdapter extends Adapter {
	
	@Resource
	private ConfigService configService;
	
	public String display(
			SysTemplate sysTemplate, 
			Model model,
			String sysTextKey) 
	{
		configService.load();
		
		PageSysTextVO pageDataVO = new PageSysTextVO(
			configService.getSysText(sysTextKey));
			
		PageSysText page = new PageSysText("page_sysText", sysTemplate, pageDataVO);
		
		page.setContent(pageDataVO.getContent());
		
		model.addAttribute("pageData", page);
		
		return WebsiteConfig.TEMPLATE_ROOT + "/" + sysTemplate.getName() + "/page_sysText";
	}
}
