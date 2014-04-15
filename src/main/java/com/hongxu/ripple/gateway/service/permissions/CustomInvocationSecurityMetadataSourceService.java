/*
 * @(#) MyInvocationSecurityMetadataSourceService.java  2011-3-23 下午02:58:29
 *
 * Copyright 2011 by Sparta 
 */

package com.hongxu.ripple.gateway.service.permissions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.PerCustomMapper;
import com.hongxu.ripplermb.domain.core.mapper.PerResourcesMapper;
import com.hongxu.ripplermb.domain.core.po.PerResources;
import com.hongxu.ripplermb.domain.core.po.PerResourcesCriteria;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {
	
	//@Resource
	//private PerAuthoritiesMapper perAuthoritiesMapper;
	@Resource
	private PerResourcesMapper perResourcesMapper;
	@Resource
	private PerCustomMapper perCustomMapper;

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
	public void loadResourceDefine() {
		
		//List<PerAuthorities> query = perAuthoritiesMapper.selectByExample(new PerAuthoritiesCriteria());
		List<PerResources> query = perResourcesMapper.selectByExample(new PerResourcesCriteria());

		/*
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		 * sparta
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		//for (PerAuthorities pa : query) {
		for(PerResources pr : query){
			//ConfigAttribute ca = new SecurityConfig(pa.getAuthorityName());
			ConfigAttribute ca = new SecurityConfig(pr.getResourceId());
			//List<String> resources = perCustomMapper.getResourceStringByAuthorityName(pa.getAuthorityName());
			//for (String res : resources) {
				String url = pr.getResourceString();
				/*
				 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				 * sparta
				 */
				if (resourceMap.containsKey(url)) {

					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}

			//}

		}

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	// 根据URL，找到相关的权限配置。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();
		
        int firstQuestionMarkIndex = url.indexOf("?");

        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }

		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();
			
			if (urlMatcher.pathMatchesUrl(url, resURL)) {

				return resourceMap.get(resURL);
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {

		return true;
	}

}
