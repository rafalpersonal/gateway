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
import com.hongxu.ripple.gateway.vo.Deposit;
import com.hongxu.ripplermb.domain.core.mapper.DepositRecordMapper;
import com.hongxu.ripplermb.domain.core.mapper.SysPaymentMapper;
import com.hongxu.ripplermb.domain.core.po.DepositRecord;
import com.hongxu.ripplermb.domain.core.po.DepositRecordCriteria;
import com.hongxu.ripplermb.domain.core.support.Page;
import com.hongxu.ripplermb.domain.core.support.PaymentStatus;

/**
 * 付款服务
 * @author 王欣
 * @Version v1.0
 */
@Service
public class DepositRecordService{

	private static Logger logger = LoggerFactory.getLogger(DepositRecordService.class);
	
	@Resource
	private DepositRecordMapper depositRecordMapper;
	@Resource
	private SysPaymentMapper sysPaymentMapper;
	@Resource
	private UserService userService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 创建付款记录
	 */
	public DepositRecord createDepositRecord(
			String userId,
			String walletAddress,
			double money,
			String currency,
			String issuer,
			double fee,
			String paymentId
			){
		String result = "";
		logger.debug("创建付款记录.参数:{},{},{}.",new Object[]{userId,money,paymentId});
		try{
			
			DepositRecord po = new DepositRecord();
			po.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			po.setUserId(userId);
			po.setWalletAddress(walletAddress);
			po.setSn(SnUtils.genOrderSn());
			po.setMoney(new BigDecimal(money));//付款金额
			po.setCurrency(currency);
			po.setIssuer(issuer);
			po.setFee(new BigDecimal(fee));//付款费率
			po.setTotal(new BigDecimal(money + fee));//应付款总金额
			po.setPaid(new BigDecimal(0.0));//实际付款金额
			po.setPaymentId(paymentId);
			po.setPmtStatus(PaymentStatus.PAYMENT_NO.toString());
			po.setCreateTime(new Date());
			
			if(depositRecordMapper.insertSelective(po)>0){
				return po;
			}
			
			return null;
			
		}catch(Exception ex){
			logger.error("创建付款记录失败."+ex.getMessage(), ex);
			return null;
		}
		
	}
	
	public boolean editDepositRecord(DepositRecord po){
		boolean result = false;
		if(depositRecordMapper.updateByPrimaryKeySelective(po)>0) result = true;
		return result;
	}
	
	
	
	/**
	 * 得到付款记录~
	 * @param id
	 * @return
	 */
	public DepositRecord getDepositRecord(String id){
		logger.debug("getPaymentRecord().params:{}.",id);
		DepositRecord po = depositRecordMapper.selectByPrimaryKey(id);
		return po;
	}
	
	/**
	 * 付款记录分页查询~
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Deposit> findDepositPage(
			String userId,
			String sn,
			String pmtStatus,
			String startTime,
			String endTime,
			int pageNo,
			int pageSize
			){
			logger.debug("findPaymentPage().");
			//查询条件~
			Page<Deposit> page = new Page<Deposit>(pageSize);
			ArrayList<Deposit> list = new ArrayList<Deposit>();
			DepositRecordCriteria c = new DepositRecordCriteria();
			DepositRecordCriteria.Criteria cc = c.createCriteria();
			
			if(StringUtils.isNotBlank(userId)) cc.andUserIdLike("%"+userId+"%");
			if(StringUtils.isNotBlank(sn)) cc.andSnLike("%"+sn+"%");
			if(StringUtils.isNotBlank(pmtStatus)) cc.andPmtStatusEqualTo(pmtStatus);
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
			page.setTotalCount(depositRecordMapper.countByExample(c));
			c.setMysqlLength(page.getPageSize());
			c.setMysqlOffset(page.getFirst());
			c.setOrderByClause("create_time DESC");
			//查询结果~
			
			List<DepositRecord> polist = depositRecordMapper.selectByExample(c);
			if(polist != null && polist.size()>0){
				for(DepositRecord po : polist){
					Deposit deposit = new Deposit();
					deposit.setDepositRecord(po);
					deposit.setUser(userService.getUser(po.getUserId()));
					//System.out.println("*******************" + userService.getUser(po.getUserId()));
					deposit.setSysPayment(sysPaymentMapper.selectByPrimaryKey(po.getPaymentId()));
					list.add(deposit);
				}
			}
			page.setResult(list);
			return page;
	}
	
}
