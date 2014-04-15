package com.hongxu.ripple.gateway.web.manager;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.PageDataService;
import com.hongxu.ripple.gateway.service.SysTemplateService;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripplermb.domain.core.po.PageData;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MTemplateController extends MBaseController{
	
	private static Logger logger = LoggerFactory.getLogger(MTemplateController.class);
	
	@Resource
	private SysTemplateService sysTemplateService;
	
	@Resource
	private PageDataService pageDataService;
	
	private ObjectMapper om = new ObjectMapper();
	
	@RequestMapping(value="/template_list.r",method={RequestMethod.GET})
	public String templateList(Model model) {
		logger.debug("templateEdit().");
		model.addAttribute("list", sysTemplateService.findSysTemplateList());
		return BASE_URL + "/template_list";
	}
	
	@RequestMapping(value="/template/page_data_module_list.r",method={RequestMethod.GET})
	public String pageDataModuleList(String templateId,Model model) {
		logger.debug("pageDataModuleList().");
		model.addAttribute("list", pageDataService.findPageDataModuleListByTemplateId(templateId));
		return BASE_URL + "/template/page_data_module_list";
	}
	
	@RequestMapping(value="/template/page_data_module_edit.r",method={RequestMethod.GET})
	public String pageDataModuleEdit(String pageDataId,Model model) {
		logger.debug("pageDataModuleEdit().");
		PageData po = pageDataService.getPageData(pageDataId);
		LinkedHashMap<String, Object> map = null;
		try {
			map = (LinkedHashMap<String, Object>)om.readValue(po.getData(), Map.class);
			//System.out.println("*************************************" + map.keySet());
			model.addAttribute("keys", map.keySet());
			
			model.addAttribute("map",map);
			model.addAttribute("d", po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return BASE_URL + "/template/page_data_module_edit";
	}
	
	@RequestMapping(value = "/template/page_data_module_edit.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String pageDataModuleEdit(String pageDataId,HttpServletRequest request,Model model){
		logger.debug("pageDataModuleEdit().params:{}.",pageDataId);
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		//DwzResult result = new DwzResult();
		try{
			Enumeration<String> names =  request.getParameterNames();
			ArrayList<LinkedHashMap<String, String>> otherItems = new ArrayList<LinkedHashMap<String, String>>();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if(!name.equals("pageDataId")){
					//itemList表单~
					if(name.startsWith("itemList_")){
						ArrayList<LinkedHashMap<String, String>> itemList = new ArrayList<LinkedHashMap<String, String>>();
						if(name.equals("itemList_name")){
							String[] valueNames = request.getParameterValues("itemList_name");
							String[] valueUrls = request.getParameterValues("itemList_url");
							
							if(valueNames != null && valueUrls != null){
								for(int i=0;i<valueNames.length;i++){
									LinkedHashMap<String, String> item = new LinkedHashMap<String, String>();
									item.put("name", valueNames[i]);
									item.put("url", valueUrls[i]);
									itemList.add(item);
								}
							}
						}
						data.put("itemList", itemList);
					}
					//otherItems表单~
					else if(name.startsWith("otherItems_")){
						if(name.equals("otherItems_name")){
							String[] valueNames = request.getParameterValues("otherItems_name");
							String[] valueUrls = request.getParameterValues("otherItems_url");
							
							if(valueNames != null && valueUrls != null){
								for(int i=0;i<valueNames.length;i++){
									LinkedHashMap<String, String> item = new LinkedHashMap<String, String>();
									item.put("name", valueNames[i]);
									item.put("url", valueUrls[i]);
									otherItems.add(item);
								}
							}
						}
					}
					//其他表单~
					//普通表单~
					else{
						String value = request.getParameter(name);
						data.put(name, value);
					}
				}
			}
			if(data.get("itemList") !=null ) data.put("otherItems", otherItems);
			
			logger.debug(">>>>>>>>>>>>>>>>>>>> data json : " + om.writeValueAsString(data));
			PageData po = new PageData();
			po.setId(pageDataId);
			po.setData(om.writeValueAsString(data));
			if(pageDataService.editPageData(po)){
			//if(1==1){
				model.addAttribute("result", true);
//				result.setStatusCode("200");
//				result.setNavTabId("page_data_module_list");
//				result.setCallbackType("closeCurrent");
//				result.setMessage(getMessage(request, "manager.message.success"));
			}else{
//				result.setStatusCode("300");
//				result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
			
		}catch(Exception ex){
			logger.error("page data module edit error.{}", ex.getMessage(), ex);
			//result.setStatusCode("300");
			//result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result",false);
		}
		PageData po = pageDataService.getPageData(pageDataId);
		LinkedHashMap<String, Object> map = null;
		try {
			map = (LinkedHashMap<String, Object>)om.readValue(po.getData(), Map.class);
			//System.out.println("*************************************" + map.keySet());
			model.addAttribute("keys", map.keySet());
			
			model.addAttribute("map",map);
			model.addAttribute("d", po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return BASE_URL + "/template/page_data_module_edit";
	}
	
	@RequestMapping(value="/template/page_data_page_list.r",method={RequestMethod.GET})
	public String pageDataPageList(String templateId,Model model) {
		logger.debug("pageDataPageList().");
		model.addAttribute("list", pageDataService.findPageDataPageListByTemplateId(templateId));
		return BASE_URL + "/template/page_data_page_list";
	}
	
	@RequestMapping(value="/template/page_data_page_edit.r",method={RequestMethod.GET})
	public String pageDataPageEdit(String pageDataId,Model model) {
		logger.debug("pageDataPageEdit().");
		PageData po = pageDataService.getPageData(pageDataId);
		LinkedHashMap<String, Object> map = null;
		try {
			map = (LinkedHashMap<String, Object>)om.readValue(po.getData(), Map.class);
			//System.out.println("*************************************" + map.keySet());
			model.addAttribute("keys", map.keySet());
			
			model.addAttribute("map",map);
			model.addAttribute("d", po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return BASE_URL + "/template/page_data_page_edit";
	}
	
	@RequestMapping(value = "/template/page_data_page_edit.r", method = {RequestMethod.POST})
	//@ResponseBody
	public String pageDataPageEdit(String pageDataId,HttpServletRequest request,Model model){
		logger.debug("pageDataPageEdit().params:{}.",pageDataId);
		LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
		//DwzResult result = new DwzResult();
		try{
			Enumeration<String> names =  request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if(!name.equals("pageDataId")){
					//sectionList表单~
					if(name.startsWith("sectionList.")){
						if(name.equals("sectionList.title")){
							String[] valueTitles = request.getParameterValues("sectionList.title");
							String[] valueContents = request.getParameterValues("sectionList.content");
							ArrayList<LinkedHashMap<String, String>> sectionList = new ArrayList<LinkedHashMap<String, String>>();
							if(valueTitles != null && valueTitles != null){
								for(int i=0;i<valueTitles.length;i++){
									LinkedHashMap<String, String> section = new LinkedHashMap<String, String>();
									section.put("title", valueTitles[i]);
									section.put("content", valueContents[i]);
									sectionList.add(section);
								}
							}
							data.put("sectionList", sectionList);
						}
					}
					//其他表单~
					//普通表单~
					else{
						String value = request.getParameter(name);
						data.put(name, value);
					}
				}
			}
			
			logger.debug(">>>>>>>>>>>>>>>>>>>> data json : " + om.writeValueAsString(data));
			PageData po = new PageData();
			po.setId(pageDataId);
			po.setData(om.writeValueAsString(data));
			if(pageDataService.editPageData(po)){
//				result.setStatusCode("200");
//				result.setNavTabId("page_data_page_list");
//				result.setCallbackType("closeCurrent");
//				result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			}else{
				//result.setStatusCode("300");
				//result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
			
		}catch(Exception ex){
			logger.error("page data module edit error.{}", ex.getMessage(), ex);
			//result.setStatusCode("300");
			//result.setMessage(getMessage(request, "manager.message.error") + " : " + ex.getMessage());
			model.addAttribute("result", false);
		}
		LinkedHashMap<String, Object> map = null;
		PageData po = pageDataService.getPageData(pageDataId);
		try {
			map = (LinkedHashMap<String, Object>)om.readValue(po.getData(), Map.class);
			//System.out.println("*************************************" + map.keySet());
			model.addAttribute("keys", map.keySet());
			
			model.addAttribute("map",map);
			model.addAttribute("d", po);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return BASE_URL + "/template/page_data_page_edit";
	}
}
