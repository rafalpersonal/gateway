package com.hongxu.ripple.gateway.web.page;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.hongxu.ripple.gateway.service.GatewayService;
import com.hongxu.ripple.gateway.service.OrganizationService;
import com.hongxu.ripple.gateway.service.PageDataService;
import com.hongxu.ripple.gateway.service.SysTemplateService;
import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.skin.PageSkin;
import com.hongxu.ripple.gateway.style.skin.WebsiteSkins;
import com.hongxu.ripple.gateway.web.BasePageDataBundle;
import com.hongxu.ripple.gateway.web.SessionBundle;
import com.hongxu.ripplermb.domain.core.po.GatewayCurrency;
import com.hongxu.ripplermb.domain.core.po.Organization;

public class BasePageController {
	
	public static final String POST_RESULT_CODE = "isOk";
	public static final String POST_RESULT_MESSAGE = "message";
	public static final String POST_RESULT_DATA = "data";
	
	@Resource
	protected SysTemplateService sysTemplateService;
	@Resource
	protected TemplateService templateService;
	@Resource
	protected PageDataService pageDataService;
	@Resource 
	protected OrganizationService orgnizationService;
	@Resource
	protected GatewayService gatewayService;
	
	protected Organization getOrg(String orgId) {
		
		return orgnizationService.getOrgnization(orgId);
	}
	
	protected PageSkin getPageSkins(Organization org, String pageName) {
		
		WebsiteSkins templateSkins = 
				sysTemplateService.getTemplateSkins(org.getTempateId());
		if(templateSkins == null) {
			return null;
		}
		
		PageSkin pageSkin = templateSkins.getPageSkin(pageName);
		if(pageSkin == null) {
			pageSkin = templateSkins.getDefaultPageSkin();
		}
		
		return pageSkin;
	}
	
	protected List<GatewayCurrency> getGatewayCurrencyList() {
		
		return gatewayService.getGatewayCurrencyList();
	}
	
	protected boolean preparePageData(
			String userId, String pageName, 
			Model model, HttpSession session) 
	{	
		Organization org = getOrg(userId);
		if(org == null) {
			List<String> messageList = new ArrayList<String>();
			messageList.add("can't get organization ! id = " + userId);
			model.addAttribute("messageList", messageList);
			return false;
		}
		
		PageSkin pageSkin = getPageSkins(
				org, pageName);	
		if(pageSkin == null) {
			List<String> messageList = new ArrayList<String>();
			messageList.add("can't get page skins  ! id = " + userId);
			model.addAttribute("messageList", messageList);
			return false;
		}
		
		List<GatewayCurrency> gatewayCurrencyList = getGatewayCurrencyList();
		if(gatewayCurrencyList == null || gatewayCurrencyList.size() == 0) {
			List<String> messageList = new ArrayList<String>();
			messageList.add("can't get currency that the gateway supports ! ");
			model.addAttribute("messageList", messageList);
			return false;
		}
		
		model.addAttribute("skins", pageSkin);
		model.addAttribute("org", org);
		
		//BasePageDataBundle dataBundle = new BasePageDataBundle();
		//dataBundle.setOrg(org);
		//dataBundle.setPageSkin(pageSkin);
		//SessionBundle.setPageDataBundle(session, dataBundle);
		SessionBundle.setGatewayCurrencyList(session, gatewayCurrencyList);
		SessionBundle.setOrganization(session, org);
		SessionBundle.setPageSkin(session, pageSkin);
		
		return true;
	}
}
