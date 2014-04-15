package com.hongxu.ripple.gateway.template.traditional.page;

import java.util.List;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.metadata.Section;
import com.hongxu.ripple.gateway.style.page.Page;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageIndex extends Page {
	
	public PageIndex(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}
	
	public void prepareData(TemplateService templateService) {
		
	} 
	
	public static class VO extends PageDataVO  {
		
		String message = null;
		List<Section> sectionList = null;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public List<Section> getSectionList() {
			return sectionList;
		}

		public void setSectionList(List<Section> sectionList) {
			this.sectionList = sectionList;
		}
		
		
	}
}
