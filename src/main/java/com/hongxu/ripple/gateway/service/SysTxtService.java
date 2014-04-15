package com.hongxu.ripple.gateway.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.SysSettingsMapper;
import com.hongxu.ripplermb.domain.core.mapper.SysTxtMapper;
import com.hongxu.ripplermb.domain.core.po.SysSettings;
import com.hongxu.ripplermb.domain.core.po.SysTxt;

@Service
public class SysTxtService {
	
	@Resource
	private SysTxtMapper sysTxtMapper;
	
	public SysTxt getSysTxt(String txtKey) {
		return sysTxtMapper.selectByPrimaryKey(txtKey);
	}
	
	public LinkedHashMap<String,SysTxt> findSysTxtMap(){
		LinkedHashMap<String, SysTxt> map = new LinkedHashMap<String, SysTxt>();
		List<SysTxt> list = sysTxtMapper.selectByExample(null);
		if(list!=null && list.size()>0){
			for(SysTxt st : list) map.put(st.getTxtKey(), st);
		}
		return map; 
	}
	
	public boolean editSysTxt(SysTxt po){
		boolean result = false;
		if(sysTxtMapper.updateByPrimaryKeyWithBLOBs(po)>0) result = true;
		return result;
	}
}
