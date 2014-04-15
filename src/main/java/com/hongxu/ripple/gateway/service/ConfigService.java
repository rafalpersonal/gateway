package com.hongxu.ripple.gateway.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.SysSettingsMapper;
import com.hongxu.ripplermb.domain.core.mapper.SysTxtMapper;
import com.hongxu.ripplermb.domain.core.po.SysSettings;
import com.hongxu.ripplermb.domain.core.po.SysSettingsCriteria;
import com.hongxu.ripplermb.domain.core.po.SysTxt;
import com.hongxu.ripplermb.domain.core.po.SysTxtCriteria;

@Service
public class ConfigService {
	
	private boolean init = false;
	@Resource
	private SysSettingsMapper settingsMapper;
	@Resource
	private SysTxtMapper txtMapper;
	
	private Map<String, SysSettings> settingsMap = new HashMap<String, SysSettings>();
	private Map<String, SysTxt> txtMap = new HashMap<String, SysTxt>();
	
	
	public boolean loadSettings() {
			
		SysSettingsCriteria criteria = new SysSettingsCriteria();
		List<SysSettings> settingsList = settingsMapper.selectByExampleWithBLOBs(criteria);
		for(int i = 0; i < settingsList.size(); i++) {
			SysSettings settings = settingsList.get(i);
			settingsMap.put(settings.getAttrName(), settings);
			
		}
		return true;
	}
	
	public boolean loadTxt() {
		
		SysTxtCriteria criteria = new SysTxtCriteria();
		List<SysTxt> txtList = txtMapper.selectByExampleWithBLOBs(criteria);
		for(int i = 0; i < txtList.size(); i++) {
			SysTxt txt = txtList.get(i);
			txtMap.put(txt.getTxtKey(), txt);
		}
		
		return true;
	}
	
	public void load() {
		if(!init) {
			loadSettings();
			loadTxt();
			init = true;
		}
	}
	
	public String getSettingValue(String settingName) {
		SysSettings settings = settingsMap.get(settingName);
		if(settings == null) {
			return null;
		}
		
		return settings.getAttrValue();
	}
	
	public SysTxt getSysText(String key) {
		return txtMap.get(key);
	}

	public List<SysTxt> getSysTextList() {
		
		List<SysTxt> sysTextList = new ArrayList<SysTxt>();
		Iterator<String> iter = txtMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			SysTxt value = txtMap.get(key);
			sysTextList.add(value);
		}
		
		return sysTextList;
	}
	
}
