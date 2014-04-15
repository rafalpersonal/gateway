package com.hongxu.ripple.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.style.module.ModuleVO;
import com.hongxu.ripple.gateway.style.page.PageDataVO;
import com.hongxu.ripple.gateway.utils.JsonUtil;
import com.hongxu.ripplermb.domain.core.mapper.PageDataMapper;
import com.hongxu.ripplermb.domain.core.po.PageData;
import com.hongxu.ripplermb.domain.core.po.PageDataCriteria;

@Service
public class PageDataService {
	
	@Resource
	private PageDataMapper pageDataMapper;
	
	public <T> T getPageDataVO(String templateId, String keyword, TypeReference<T> typeRef) {
		
		try{
			PageDataCriteria criteria = new PageDataCriteria();
			PageDataCriteria.Criteria cri = criteria.createCriteria();
			cri.andTemplateIdEqualTo(templateId);
			cri.andKeywordEqualTo(keyword);
			
			List<PageData> pageDataList = pageDataMapper.selectByExampleWithBLOBs(criteria);
			if(pageDataList.size() == 0) {
				return null;
			}
			
			PageData pageData = pageDataList.get(0);
			String jsonStr = pageData.getData();
			T vo = JsonUtil.objFromString(jsonStr, typeRef);
			((PageDataVO)vo).setName(pageData.getName());
			return vo;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public <T> T getModuleDataVO(String templateId, String keyword, TypeReference<T> typeRef) {
		
		try{
			PageDataCriteria criteria = new PageDataCriteria();
			PageDataCriteria.Criteria cri = criteria.createCriteria();
			cri.andTemplateIdEqualTo(templateId);
			cri.andKeywordEqualTo(keyword);
			
			List<PageData> pageDataList = pageDataMapper.selectByExampleWithBLOBs(criteria);
			if(pageDataList.size() == 0) {
				return null;
			}
			
			PageData pageData = pageDataList.get(0);
			String jsonStr = pageData.getData();
			T vo = JsonUtil.objFromString(jsonStr, typeRef);
			((ModuleVO)vo).setName(pageData.getName());
			return vo;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PageData> findPageDataModuleListByTemplateId(String templateId){
		PageDataCriteria c = new PageDataCriteria();
		c.createCriteria()
			.andTemplateIdEqualTo(templateId)
			.andKeywordLike("%module_%");
			;
		c.setOrderByClause("display_order ASC");
		return pageDataMapper.selectByExample(c);
	}
	
	public List<PageData> findPageDataPageListByTemplateId(String templateId){
		PageDataCriteria c = new PageDataCriteria();
		c.createCriteria()
			.andTemplateIdEqualTo(templateId)
			.andKeywordLike("%page_%");
			;
		c.setOrderByClause("display_order ASC");
		return pageDataMapper.selectByExample(c);
	}
	
	public PageData getPageData(String id){
		return pageDataMapper.selectByPrimaryKey(id);
	}
	
	public boolean editPageData(PageData po){
		boolean result = false;
		if(pageDataMapper.updateByPrimaryKeySelective(po)>0) result = true;
		return result;
	}
}
