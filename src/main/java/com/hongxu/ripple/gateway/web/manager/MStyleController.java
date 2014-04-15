package com.hongxu.ripple.gateway.web.manager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.OrganizationService;
import com.hongxu.ripple.gateway.service.SysTemplateService;
import com.hongxu.ripple.gateway.service.SysTxtService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripplermb.domain.core.po.Organization;
import com.hongxu.ripplermb.domain.core.po.SysSettings;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;
import com.hongxu.ripplermb.domain.core.po.SysTxt;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MStyleController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MStyleController.class);
	
	@Resource
	private OrganizationService organizationService;

	@Resource
	private SysTemplateService sysTemplateService;
	
	
	@RequestMapping(value = "/style_manage.r",method={RequestMethod.GET,RequestMethod.POST})
	public String styleManage(Model model,HttpServletRequest request) {
		logger.debug("styleManage().");
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		Organization org = organizationService.getOrgnization(TemplateService.orgId);
		SysTemplate temp = sysTemplateService.getTemplate(org.getTempateId());
		String template = temp.getName();
		String cssContentPath= realPath + File.separator + "static" + File.separator + "skins" + File.separator + template + File.separator + "css" + File.separator  + "style.css";
		String styleContent = "";
		StringBuffer sb = new StringBuffer();
		File f = new File(cssContentPath);
		logger.error("解析css文件["+cssContentPath+"].");
		try{
			if (f.exists()) {
	        	FileReader fr = new FileReader(f);
	        	int c = 0;
	        	do {
	        		c = fr.read();
	        		if (c != -1) sb.append((char) c);
	        	} while (c != -1);
	        	fr.close();
	        }
			styleContent = sb.toString();
		}catch(Exception ex){
			logger.error("解析css文件出错.",ex);
		}
		if(StringUtils.isNotBlank(styleContent)){
			model.addAttribute("canEdit", true);
		}else{
			model.addAttribute("canEdit", false);
		}
		model.addAttribute("template", template);
		model.addAttribute("styleContent", styleContent);
		return BASE_URL + "/style_manage";
	}
	
	@RequestMapping(value = "/style_restore_original.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String styleRestoreOriginal(String template,HttpServletRequest request,Model model){
		logger.debug("styleRestoreOriginal().params:{},{}.",template);
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String cssBackupContentPath = realPath + File.separator + "static" + File.separator + "skins" + File.separator + template + File.separator + "css" + File.separator  + "style.css.backup";
		String cssContentPath= realPath + File.separator + "static" + File.separator + "skins" + File.separator + template + File.separator + "css" + File.separator  + "style.css";
		//DwzResult result = new DwzResult();
		try{
			File backupCss = new File(cssBackupContentPath);
			File css = new File(cssContentPath);
			//还原
			if(backupCss.exists() && css.exists()){
				FileUtils.copyFile(backupCss, css);
//				result.setStatusCode("200");
//				result.setNavTabId("FUNCTION_STYLE_MANAGE");
//				result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			}else{
//				result.setStatusCode("300");
//				result.setNavTabId("FUNCTION_STYLE_MANAGE");
//				result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
		}catch(Exception ex){
			logger.error("style edit error.{}", ex.getMessage(), ex);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		
		return styleManage(model, request);
	}
	
	@RequestMapping(value = "/style_edit.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String styleEdit(String template,String styleContent,HttpServletRequest request,Model model){
		logger.debug("styleEdit().params:{},{}.",template,styleContent);
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String cssBackupContentPath = realPath + File.separator + "static" + File.separator + "skins" + File.separator + template + File.separator + "css" + File.separator  + "style.css.backup";
		String cssContentPath= realPath + File.separator + "static" + File.separator + "skins" + File.separator + template + File.separator + "css" + File.separator  + "style.css";
		//DwzResult result = new DwzResult();
		try{
			File backupCss = new File(cssBackupContentPath);
			File css = new File(cssContentPath);
			//如果备份文件不存在，首先创建备份文件
			if(!backupCss.exists() && css.exists()){
				//backupCss.createNewFile();
				//css.renameTo(backupCss);
				FileUtils.copyFile(css, backupCss);
			}
	        if (css.exists()) {
	                FileWriter fw = new FileWriter(css);
	                fw.write(styleContent);
	                fw.close();
//	                result.setStatusCode("200");
//					result.setNavTabId("FUNCTION_STYLE_MANAGE");
//					result.setMessage(getMessage(request, "manager.message.success"));
	                model.addAttribute("result", true);
	        }
		}catch(Exception ex){
			logger.error("style edit error.{}", ex.getMessage(), ex);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		//return result;
		return styleManage(model, request);
	}
}
