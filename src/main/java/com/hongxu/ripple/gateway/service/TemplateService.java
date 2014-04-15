package com.hongxu.ripple.gateway.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.po.Organization;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.SysTxt;

@Service(value="templateService")
public class TemplateService {
	
	public static final String orgId = "1";
	public static final String TYPE_SIMPLIFY = "Simplify";
	public static final String TYPE_TRADITIONAL = "Traditional";
	
	
	
	@Resource
	protected ConfigService configService;
	@Resource
	protected OrganizationService orgService;
	@Resource
	protected NewsService newsService;
	@Resource
	protected ArticleService articleService;
	@Resource
	protected SysTemplateService sysTemplateService;
//	@Resource
//	protected PageDataService pageDataService;
	
	protected static boolean inited = false;
	protected static Organization org = null;
	protected static SysTemplate sysTemplate = null;
	protected static List<SysTxt> sysTextList = null;
	
	public void initTemplate() {
		
		if(!inited) {
			configService.load();
			org = loadOrg(orgId);
			sysTemplate = loadSysTemplate(org.getTempateId());
			sysTextList = configService.getSysTextList();
			inited = true;
		}
	}
	
	public void reload() {
		
		configService.load();
		org = loadOrg(orgId);
		sysTemplate = loadSysTemplate(org.getTempateId());
		sysTextList = configService.getSysTextList();
		inited = true;
	}
	
	private Organization loadOrg(String orgId) {
		
		return orgService.getOrgnization(orgId);
	}
	
	private SysTemplate loadSysTemplate(String templateId) {
		return sysTemplateService.getTemplate(templateId);
	}
	
	public Organization getOrg() {
		return org;
	}

	public SysTemplate getSysTemplate() {
		initTemplate();
		return sysTemplate;
	}

	public String getSettingValue(String settingKey) {
		initTemplate();
		return configService.getSettingValue(settingKey);
	}
}
