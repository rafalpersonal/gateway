package com.hongxu.ripple.gateway.web.manager;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hongxu.ripple.gateway.service.SysSettingsService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.utils.Constants;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripplermb.domain.core.po.SysSettings;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MSettingsController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MSettingsController.class);
	
	@Resource
	private SysSettingsService sysSettingsService;
	@Resource
	private TemplateService templateService;

	@RequestMapping(value="/settings_manage.r",method={RequestMethod.GET,RequestMethod.POST})
	public String settingsManage(Model model) {
		logger.debug("settingsManage().");
		model.addAttribute("map", sysSettingsService.findSysSettingsMap());
		model.addAttribute("languages", Constants.Languages.values());
		return BASE_URL + "/settings_manage";
	}
	
	@RequestMapping(value = "/settings_edit.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String settingsEdit(SysSettings po,HttpServletRequest request,Model model){
		logger.debug("settingsEdit().params:{}.",po);

		//DwzResult result = new DwzResult();
		try{
			if(sysSettingsService.editSysSettings(po)){
				systemReload();
				//result.setStatusCode("200");
				//result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			}else{
				//result.setStatusCode("300");
				//result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
			
		}catch(Exception ex){
			logger.error("settings edit error.{}", ex.getMessage(), ex);
			//result.setStatusCode("300");
			//result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		//return result;
		model.addAttribute("map", sysSettingsService.findSysSettingsMap());
		model.addAttribute("languages", Constants.Languages.values());
		return BASE_URL + "/settings_manage";
	}
	
	@RequestMapping(value = "/settings_edit_logo.r", method = RequestMethod.POST)
	//@ResponseBody
	public String settingsEditLogo(String navTabId,String attrName,String attrType, @RequestParam("attrValue") CommonsMultipartFile attrValue,HttpServletRequest request,Model model) {
		logger.debug("settingsEdit().params:{},{},{}.",new Object[]{navTabId,attrName,attrType});
		//DwzResult result = new DwzResult();
		try{
			if(attrValue.isEmpty()){
//				result.setStatusCode("300");
//				result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}else{
				String realPath = request.getSession().getServletContext().getRealPath("/");
				String uploadPath = "/upload/";
				//String mainName = "logo_"+UUID.randomUUID().toString().replaceAll("-", "");
				String mainName = "logo";
				String extName = "." + FilenameUtils.getExtension(attrValue.getOriginalFilename());
				File file = new File(realPath+uploadPath + mainName + extName); // 新建一个文件
				attrValue.getFileItem().write(file);
				SysSettings po = new SysSettings();
				po.setAttrName(attrName);
				po.setAttrType(attrType);
				po.setAttrValue(uploadPath + mainName + extName);
				logger.debug("upload success:{}.", po);
				if(sysSettingsService.editSysSettings(po)){		
					systemReload();
//					result.setStatusCode("200");
//					result.setNavTabId(navTabId);
//					//result.setRel(navTabId);
//					//result.setCallbackType("null");
//					result.setMessage(getMessage(request, "manager.message.success"));
					model.addAttribute("result", true);
				}else{
//					result.setStatusCode("300");
//					result.setMessage(getMessage(request, "manager.message.failure"));
					model.addAttribute("result", false);
				}
			}
		}catch(Exception ex){
			logger.error("settings edit logo error.{}", ex.getMessage(), ex);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		//return result;
		model.addAttribute("map", sysSettingsService.findSysSettingsMap());
		model.addAttribute("languages", Constants.Languages.values());
		return BASE_URL + "/settings_manage";
	}
	
	private void systemReload() {
		templateService.reload();
	}
}
