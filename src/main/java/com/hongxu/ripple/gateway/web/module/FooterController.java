package com.hongxu.ripple.gateway.web.module;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.service.TemplateService;
import com.hongxu.ripple.gateway.style.WebsiteConfig;
import com.hongxu.ripple.gateway.style.module.footer.Footer;
import com.hongxu.ripple.gateway.style.module.footer.ModuleFooter;
import com.hongxu.ripplermb.domain.core.po.SysTemplate;

@Controller(value="footerController")
@RequestMapping(value = FooterController.MODULE_PATH)
public class FooterController extends BaseModuleController {
	
	public final static String MODULE_PATH = MODULE_ROOT + "/footer";
	
	@RequestMapping(value = "/footer_b.html", method = RequestMethod.GET)
	public String footer(
			String styleType,
			HttpServletRequest request,
			Model model)
	{
		SysTemplate sysTemplate = 
				templateService.getSysTemplate();
	
		TypeReference<com.hongxu.ripple.gateway.style.module.footer.ModuleFooter.VO> typeRef =
				new TypeReference<com.hongxu.ripple.gateway.style.module.footer.ModuleFooter.VO>() {};
			
		ModuleFooter.VO vo = pageDataService.getModuleDataVO(
				sysTemplate.getId(),
				WebsiteConfig.ModuleKeyForDisplay.MODULE_FOOTER,
				typeRef);
		
		ModuleFooter moduleFooter = new ModuleFooter(sysTemplate, vo);
		model.addAttribute("hongxu_footer", moduleFooter);
		if(Footer.TYPE_LINK_SINGLE_LINE.equals(styleType)) {
			return MODULE_PATH + "/single_line";
			
		}
		
		return MODULE_ROOT + "/dummy_module"; 
	}
}
