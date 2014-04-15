package com.hongxu.ripple.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.ArticleInfoMapper;
import com.hongxu.ripplermb.domain.core.po.ArticleInfo;
import com.hongxu.ripplermb.domain.core.po.ArticleInfoCriteria;

@Service
public class ArticleService {

	@Resource
	private ArticleInfoMapper articleMapper;
	
	public List<ArticleInfo> getArticleList() {
		
		ArticleInfoCriteria criteria = new ArticleInfoCriteria();
		criteria.setOrderByClause(" create_time asc ");
		List<ArticleInfo> articleList = articleMapper.selectByExample(criteria);
		
		return articleList;
	}
}
