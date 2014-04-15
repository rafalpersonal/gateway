package com.hongxu.ripple.gateway.web.manager;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.SysTxtService;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripplermb.domain.core.po.SysSettings;
import com.hongxu.ripplermb.domain.core.po.SysTxt;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MTxtController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MTxtController.class);
	
	@Resource
	private SysTxtService sysTxtService;
	
	@RequestMapping(value = "/txt_manage.r",method={RequestMethod.GET,RequestMethod.POST})
	public String txtManage(Model model) {
		logger.debug("txtManage().");
		LinkedHashMap<String, SysTxt> map = sysTxtService.findSysTxtMap();
		model.addAttribute("map", map);
		model.addAttribute("keys", map.keySet());
		return BASE_URL + "/txt_manage";
	}
	
	@RequestMapping(value = "/txt_edit.r",method={RequestMethod.GET})
	public String txtEdit(String txtKey,Model model) {
		logger.debug("txtManage().params:{}.",txtKey);
		model.addAttribute("po", sysTxtService.getSysTxt(txtKey));
		return BASE_URL + "/txt_edit";
	}
	
	@RequestMapping(value = "/txt_edit.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String txtEdit(SysTxt po,HttpServletRequest request,Model model){
		logger.debug("txtEdit().params:{}.",po);

		//DwzResult result = new DwzResult();
		try{
			if(sysTxtService.editSysTxt(po)){
//				result.setStatusCode("200");
//				result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			}else{
//				result.setStatusCode("300");
//				result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
			
		}catch(Exception ex){
			logger.error("txt edit error.{}", ex.getMessage(), ex);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		return txtEdit(po.getTxtKey(), model);
		//return result;
	}
}
