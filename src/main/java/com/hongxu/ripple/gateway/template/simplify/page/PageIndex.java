package com.hongxu.ripple.gateway.template.simplify.page;

import java.util.List;

import com.hongxu.ripple.gateway.style.metadata.Section;
import com.hongxu.ripple.gateway.style.page.Page;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripplermb.domain.core.po.Organization;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

public class PageIndex extends Page {
	
	private Organization org = null;
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public PageIndex(SysTemplate sysTemplate, PageDataVO vo) {
		super(sysTemplate, vo);
	}
	
	public static class VO extends PageDataVO {
		
		private String title = null;
		private String description = null;
		private String message = null;
		private List<Section> sectionList = null;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
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
