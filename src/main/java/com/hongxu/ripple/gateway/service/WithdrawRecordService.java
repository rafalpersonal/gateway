package com.hongxu.ripple.gateway.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.utils.SnUtils;
import com.hongxu.ripple.gateway.vo.Withdraw;
import com.hongxu.ripplermb.domain.core.mapper.SysPaymentMapper;
import com.hongxu.ripplermb.domain.core.mapper.UserMapper;
import com.hongxu.ripplermb.domain.core.mapper.WithdrawRecordMapper;
import com.hongxu.ripplermb.domain.core.po.WithdrawRecord;
import com.hongxu.ripplermb.domain.core.po.WithdrawRecordCriteria;
import com.hongxu.ripplermb.domain.core.support.Page;
import com.hongxu.ripplermb.domain.core.support.WithdrawStatus;

/**
 * 提现服务
 * @author 王欣
 * @Version v1.0
 */
@Service
public class WithdrawRecordService{

	private static Logger logger = LoggerFactory.getLogger(WithdrawRecordService.class);
	
	@Resource
	private WithdrawRecordMapper withdrawRecordMapper;
	
	@Resource
	private SysPaymentMapper sysPaymentMapper;
	@Resource
	private UserMapper userMapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 创建提现记录
	 */
	public String createWithdraw(
			String userId,
			String walletAddress,
			double money,
			String currency,
			String issuer,
			double fee,
			String wdAccount,
			String wdUser,
			String paymentId,
			String tag
			){
		String result = "";
		logger.debug("创建提现记录.参数:{},{},{}.",new Object[]{userId,money,paymentId});
		try{
			
			WithdrawRecord po = new WithdrawRecord();
			po.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			po.setUserId(userId);
			po.setWalletAddress(walletAddress);
			po.setSn(SnUtils.genOrderSn());
			po.setMoney(new BigDecimal(money));//提现金额
			po.setCurrency(currency);
			po.setIssuer(issuer);
			po.setFee(new BigDecimal(fee));//提现费率
			po.setTotal(new BigDecimal(money - fee));//应提现总金额
			po.setPaid(new BigDecimal(0.0));//实际提现金额
			po.setWdAccount(wdAccount);
			po.setWdUser(wdUser);
			po.setPaymentId(paymentId);
			po.setWdStatus(WithdrawStatus.WITHDRAW_NO.toString());
			po.setCreateTime(new Date());
			po.setTag(tag);
			if(withdrawRecordMapper.insertSelective(po)>0){
				result = po.getSn();
			}
		}catch(Exception ex){
			logger.error("创建提现记录失败."+ex.getMessage(), ex);
		}
		return result;
	}
	
	public boolean editWithdrawRecord(WithdrawRecord po){
		boolean result = false;
		if(withdrawRecordMapper.updateByPrimaryKeySelective(po)>0) result = true;
		return result;
	}
	
	
	/**
	 * 得到提现记录~
	 * @param id
	 * @return
	 */
	public WithdrawRecord getWithdrawRecord(String id){
		logger.debug("getWithdrawRecord().params:{}.",id);
		WithdrawRecord po = withdrawRecordMapper.selectByPrimaryKey(id);
		return po;
	}
	
	/**
	 * 提现记录分页查询~
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Withdraw> findWithdrawPage(
			String userId,
			String sn,
			String wdAccount,
			String wdStatus,
			String startTime,
			String endTime,
			int pageNo,
			int pageSize
			){
			logger.debug("findWithdrawPage().");
			//查询条件~
			Page<Withdraw> page = new Page<Withdraw>(pageSize);
			ArrayList<Withdraw> list = new ArrayList<Withdraw>();
			
			WithdrawRecordCriteria c = new WithdrawRecordCriteria();
			WithdrawRecordCriteria.Criteria cc = c.createCriteria();
			
			if(StringUtils.isNotBlank(userId)) cc.andUserIdLike("%"+userId+"%");
			if(StringUtils.isNotBlank(sn)) cc.andSnLike("%"+sn+"%");
			if(StringUtils.isNotBlank(wdAccount)) cc.andWdAccountLike("%"+wdAccount+"%");
			if(StringUtils.isNotBlank(wdStatus)) cc.andWdStatusEqualTo(wdStatus);
			if(StringUtils.isNotBlank(startTime)){
				try{
					Date st = sdf.parse(startTime);
					cc.andCreateTimeGreaterThanOrEqualTo(st);
				}catch(Exception ex){}
				
			}
			if(StringUtils.isNotBlank(endTime)){
				try{
					Date et = sdf.parse(endTime);
					cc.andCreateTimeLessThanOrEqualTo(et);
				}catch(Exception ex){}
				
			}
			//分页参数~
			page.setPageNo(pageNo);
			page.setTotalCount(withdrawRecordMapper.countByExample(c));
			c.setMysqlLength(page.getPageSize());
			c.setMysqlOffset(page.getFirst());
			c.setOrderByClause("create_time DESC");
			//查询结果~
			List<WithdrawRecord> polist = withdrawRecordMapper.selectByExample(c);
			if(polist != null && polist.size()>0){
				for(WithdrawRecord po : polist){
					Withdraw withdraw = new Withdraw();
					withdraw.setWithdrawRecord(po);
					withdraw.setUser(userMapper.selectByPrimaryKey(po.getUserId()));
					withdraw.setSysPayment(sysPaymentMapper.selectByPrimaryKey(po.getPaymentId()));
					list.add(withdraw);
				}
			}
			page.setResult(list);
			return page;
	}
	
}
