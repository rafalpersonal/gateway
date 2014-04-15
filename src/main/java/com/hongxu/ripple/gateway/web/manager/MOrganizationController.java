package com.hongxu.ripple.gateway.web.manager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hongxu.ripple.gateway.service.OrganizationService;
import com.hongxu.ripple.gateway.service.SysTemplateService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.vo.permissions.DwzResult;
import com.hongxu.ripplermb.domain.core.po.Organization;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MOrganizationController extends MBaseController {

	private static Logger logger = LoggerFactory
			.getLogger(MOrganizationController.class);

	@Resource
	private OrganizationService organizationService;

	@Resource
	private SysTemplateService sysTemplateService;
	
	@Resource
	private TemplateService templateService;

	@RequestMapping(value = "/organization_manage.r", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String organizationManage(Model model) {
		logger.debug("organizationManage().");
		model.addAttribute("tlist", sysTemplateService.findSysTemplateList());
		model.addAttribute("d", organizationService.getOrgnization(templateService.orgId));
		return BASE_URL + "/organization_manage";
	}

	@RequestMapping(value = "/organization_edit.r", method = { RequestMethod.POST })
	//@ResponseBody
	public String organizationEdit(Organization po,
			HttpServletRequest request, Model model) {
		logger.debug("settingsEdit().params:{}.", po);

		//DwzResult result = new DwzResult();
		try {
			if (organizationService.editOrgnization(po)) {
				//result.setStatusCode("200");
				//result.setMessage(getMessage(request, "manager.message.success"));
				model.addAttribute("result", true);
			} else {
				//result.setStatusCode("300");
				//result.setMessage(getMessage(request, "manager.message.failure"));
				model.addAttribute("result", false);
			}
			
			templateService.reload();

		} catch (Exception ex) {
			logger.error("settings edit error.{}", ex.getMessage(), ex);
			model.addAttribute("result", false);
//			result.setStatusCode("300");
//			result.setMessage(getMessage(request, "manager.message.error")
//					+ " : " + ex.getMessage());
		}
		model.addAttribute("tlist", sysTemplateService.findSysTemplateList());
		model.addAttribute("d", organizationService.getOrgnization(TemplateService.orgId));
		return BASE_URL + "/organization_manage";
	}
}
