package com.hongxu.ripple.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.style.skin.WebsiteSkins;
import com.hongxu.ripple.gateway.utils.JsonUtil;
import com.hongxu.ripplermb.domain.core.mapper.SysTemplateMapper;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.SysTemplateCriteria;

@Service
public class SysTemplateService {
	
	@Resource
	private SysTemplateMapper sysTemplateMapper;

	public SysTemplate getTemplate(String templateId) {
		
		return sysTemplateMapper.selectByPrimaryKey(templateId);
	}
	
	public List<SysTemplate> findSysTemplateList(){
		SysTemplateCriteria c = new SysTemplateCriteria();
		c.setOrderByClause("id ASC");
		return sysTemplateMapper.selectByExample(c);
	}
	
	public WebsiteSkins getTemplateSkins(String templateId) {
		
		try{
			SysTemplate sysTemplate = 
					sysTemplateMapper.selectByPrimaryKey(templateId);
			
			String jsonMessage = sysTemplate.getContent();
			
			TypeReference<WebsiteSkins> typeRef = 
					new TypeReference<WebsiteSkins>(){};
			WebsiteSkins websiteSkins = 
					JsonUtil.objFromString(jsonMessage, typeRef);
			
			return websiteSkins;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
