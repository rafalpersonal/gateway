/**
 * @Title BaseController.java
 * @Author 王欣
 * @Version v1.0
 * @Date 2013-4-30 下午11:21:34
 */
package com.hongxu.ripple.gateway.web.manager;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.hongxu.ripple.gateway.vo.permissions.LoginUser;
import com.hongxu.ripplermb.domain.core.po.PerUsers;

/**
 * @author 王欣
 * @Version v1.0
 * @Date 2013-4-30 下午11:21:34
 */
public abstract class MBaseController {
	
	protected static final String BASE_URL = "/manager";
	
	@Resource
	protected ResourceBundleMessageSource messageSource;
	
	/**
	 * 获取用户信息
	 * @return
	 */
	protected LoginUser getLoginUser(){
		LoginUser loginUser = new LoginUser();
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth.getPrincipal() instanceof PerUsers) {
			loginUser = (LoginUser) auth.getPrincipal();
		}
		return loginUser;
	}
	
	protected String getMessage(HttpServletRequest request,String code) {
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);  
		String value = messageSource.getMessage(code,null, locale);
		return value;
	}
}
