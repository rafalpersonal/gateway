package com.hongxu.ripple.gateway.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.SysSettingsMapper;
import com.hongxu.ripplermb.domain.core.po.SysSettings;

@Service
public class SysSettingsService {
	
	@Resource
	private SysSettingsMapper sysSettingsMapper;
	
	public SysSettings getSysSettings(String attrName) {
		return sysSettingsMapper.selectByPrimaryKey(attrName);
	}
	
	public HashMap<String,SysSettings> findSysSettingsMap(){
		HashMap<String, SysSettings> map = new HashMap<String, SysSettings>();
		List<SysSettings> list = sysSettingsMapper.selectByExampleWithBLOBs(null);
		if(list!=null && list.size()>0){
			for(SysSettings ss : list) map.put(ss.getAttrName(), ss);
		}
		return map; 
	}
	
	public boolean editSysSettings(SysSettings po){
		boolean result = false;
		if(sysSettingsMapper.updateByPrimaryKeyWithBLOBs(po)>0) result = true;
		return result;
	}
}
