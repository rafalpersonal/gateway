package com.hongxu.ripple.gateway.web.manager;
/**
 * MWithdrawRecordController
 */
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.service.WithdrawRecordService;
import com.hongxu.ripple.gateway.vo.Withdraw;
import com.hongxu.ripplermb.domain.core.support.Page;
import com.hongxu.ripplermb.domain.core.support.WithdrawStatus;

@Controller
@RequestMapping(value = MWithdrawRecordController.BASE_URL)
public class MWithdrawRecordController  extends MBaseController{

	private static Logger logger = LoggerFactory.getLogger(MWithdrawRecordController.class);

	@Resource
	private WithdrawRecordService withdrawRecordService;
	
	
	
	@RequestMapping(value = "/withdraw_record_list.r", method = {RequestMethod.GET,RequestMethod.POST})
	public String withdrawRecordList(
													String userId,
													String sn,
													String wdAccount,
													String wdStatus,
													Page<Withdraw> page,
													Model model
													) {
		logger.debug("withdrawRecordList().");
		
		Page<Withdraw> pageData = withdrawRecordService.findWithdrawPage(userId, sn,wdAccount, wdStatus, null, null, page.getPageNo(),page.getPageSize());
		model.addAttribute("page", pageData);
		model.addAttribute("withdrawStatusKeys", WithdrawStatus.inverseMapping().keySet());
		model.addAttribute("withdrawStatus", WithdrawStatus.inverseMapping());
		model.addAttribute("userId", userId);
		model.addAttribute("sn", sn);
		model.addAttribute("wdAccount", wdAccount);
		
		return BASE_URL+"/withdraw_record_list";
	}
}
