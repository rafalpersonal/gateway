/**
 * @Title MyAcceptHeaderLocaleResolver.java
 * @Package com.hongxu.ripple.gateway.interceptor
 * @Project ripple-gateway
 * @Author 王欣
 * @Version v1.0
 * @Date 2014-1-10 下午2:55:03
 */
package com.hongxu.ripple.gateway.interceptor;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.utils.Constants.Languages;

public class MyAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {

	private Locale myLocal;
	@Resource
	private TemplateService templateService;
	
	public Locale resolveLocale(HttpServletRequest request) {
		
		templateService.initTemplate();
		
		//sysSettingsService = (SysSettingsService)WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean("sysSettingsService");
		String language = templateService.getSettingValue(Settings.LANGUAGE);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>" + ss.toString());
		switch(Languages.valueOf(language)){
			case en_US:
				myLocal = Locale.US;
				break;
			case zh_CN:
				myLocal = Locale.CHINESE;
				break;
		}
		
		return myLocal;
	}

	public void setLocale(HttpServletRequest request,HttpServletResponse response, Locale locale) {
		myLocal = Locale.US;
	}

}
