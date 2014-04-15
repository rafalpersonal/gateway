package com.hongxu.ripple.gateway.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hongxu.ripplermb.domain.core.mapper.RippleRestTradeMapper;
import com.hongxu.ripplermb.domain.core.po.RippleRestTrade;
import com.hongxu.ripplermb.domain.core.po.RippleRestTradeCriteria;
import com.hongxu.ripplermb.domain.core.support.Page;

/**
* RippleRestTrade服务
* 
* @author 王欣
* @version v1.0
* @copy 鸿旭图码
*/
@Service
public class RippleRestTradeService {
	private static Logger logger = LoggerFactory.getLogger(RippleRestTradeService.class);
	
	@Resource
	private RippleRestTradeMapper rippleRestTradeMapper;
	
	public RippleRestTrade getRippleRestTrade(String id){
		logger.debug("RippleRestTrade().params:{}.",id);
		RippleRestTrade po = rippleRestTradeMapper.selectByPrimaryKey(id);
		return po;
	}
	
	public boolean addRippleRestTrade(RippleRestTrade po){
		boolean result = false;
		if(rippleRestTradeMapper.insertSelective(po)>0) result = true;
		return result;
	}
	
	public boolean deleteRippleRestTrade(String id){
		boolean result = false;
		if(rippleRestTradeMapper.deleteByPrimaryKey(id) >0 ) result = true;
		return result;
	}
	
	/**
	 * 分页查询~
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<RippleRestTrade> findRippleRestTradePage(
			String srcAddress,
			String dstAddress,
			int pageNo,
			int pageSize
			){
			logger.debug("findRippleRestTradePage().params:{},{},{},{}.",new Object[]{srcAddress,dstAddress,pageNo,pageSize});
			//查询条件~
			Page<RippleRestTrade> page = new Page<RippleRestTrade>(pageSize);
			RippleRestTradeCriteria c = new RippleRestTradeCriteria();
			RippleRestTradeCriteria.Criteria cc = c.createCriteria();
			if(StringUtils.isNotBlank(srcAddress)) cc.andSrcAddressEqualTo(srcAddress);
			if(StringUtils.isNotBlank(dstAddress)) cc.andSrcAddressEqualTo(dstAddress);
			
			//分页参数~
			page.setPageNo(pageNo);
			page.setTotalCount(rippleRestTradeMapper.countByExample(c));
			c.setMysqlLength(page.getPageSize());
			c.setMysqlOffset(page.getFirst());
			c.setOrderByClause("create_time DESC");
			//查询结果~
			List<RippleRestTrade> list = rippleRestTradeMapper.selectByExample(c);
			page.setResult(list);
			return page;
	}
}