package com.hongxu.ripple.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.NewsInfoMapper;
import com.hongxu.ripplermb.domain.core.po.NewsInfo;
import com.hongxu.ripplermb.domain.core.po.NewsInfoCriteria;

@Service
public class NewsService {

	@Resource
	private NewsInfoMapper newsMapper;
	
	public NewsInfo getLastestNews() {
		
		NewsInfoCriteria criteria = new NewsInfoCriteria();
		criteria.setOrderByClause(" create_time desc ");
		criteria.setMysqlLength(1);
		List<NewsInfo> newsList = newsMapper.selectByExample(criteria);
		
		if(newsList.size() > 0) {
			return newsList.get(0);
		}
		
		return null;
	}
}
