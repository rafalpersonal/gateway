package com.hongxu.ripple.gateway.web.manager;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.UserExtendFieldCfgService;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripplermb.domain.core.po.UserExtendFieldCfg;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MUserExtendFieldCfgController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MUserExtendFieldCfgController.class);
	
	@Resource
	private UserExtendFieldCfgService userExtendFieldCfgService;
	
	@RequestMapping(value = "/user_extend_field_cfg_list.r",method={RequestMethod.GET,RequestMethod.POST})
	public String userExtendFieldCfgList(Model model) {
		logger.debug("txtManage().");
		List<UserExtendFieldCfg> list = userExtendFieldCfgService.findUserExtendFieldCfgList();
		model.addAttribute("list", list);
		return BASE_URL + "/user_extend_field_cfg_list";
	}
	
	@RequestMapping(value = "/user_extend_field_cfg_add.r",method={RequestMethod.GET})
	public String userExtendFieldCfgAdd(Model model) {
		logger.debug("userExtendFieldCfgAdd().params:{}.");
		return BASE_URL + "/user_extend_field_cfg_add";
	}
	
	@RequestMapping(value = "/user_extend_field_cfg_add.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String userExtendFieldCfgAdd(UserExtendFieldCfg po,HttpServletRequest request,Model model){
		logger.debug("userExtendFieldCfgAdd().params:{}.",po);

		//DwzResult result = new DwzResult();
		try{
			if(userExtendFieldCfgService.addUserExtendField(po)){
//				result.setStatusCode("200");
//				result.setNavTabId("BUSINESS_USER_EXTEND_FIELD_MANAG");
//				result.setCallbackType("closeCurrent");
//				result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			}else{
//				result.setStatusCode("300");
//				result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
			
		}catch(Exception ex){
			logger.error("userExtendFieldCfgAdd() error.{}", ex.getMessage(), ex);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		//return result;
		return BASE_URL + "/user_extend_field_cfg_add";
	}
	
	@RequestMapping(value = "/user_extend_field_cfg_delete.r", method = {RequestMethod.GET,RequestMethod.POST})
	//@ResponseBody
	public String userExtendFieldCfgDelete(String id,HttpServletRequest request,Model model){
		logger.debug("userExtendFieldCfgDelete().params:{}.",id);
		//DwzResult result = new DwzResult();
		try{
			if(userExtendFieldCfgService.deleteUserExtendField(id)){
//				result.setStatusCode("200");
//				result.setNavTabId("BUSINESS_USER_EXTEND_FIELD_MANAG");
//				result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			}else{
//				result.setStatusCode("300");
//				result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
		}catch(Exception ex){
			logger.error("userExtendFieldCfgDelete() error.{}", ex.getMessage(), ex);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		//return result;
		List<UserExtendFieldCfg> list = userExtendFieldCfgService.findUserExtendFieldCfgList();
		model.addAttribute("list", list);
		return BASE_URL + "/user_extend_field_cfg_list";
	}
}
