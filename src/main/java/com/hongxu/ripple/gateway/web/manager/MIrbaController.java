package com.hongxu.ripple.gateway.web.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = MPassportController.BASE_URL)
public class MIrbaController extends MBaseController {

	private static Logger logger = LoggerFactory
			.getLogger(MIrbaController.class);

	@RequestMapping(value = "/apply_for_irba.r", method = RequestMethod.GET)
	public String appyGet(Model model) {
		logger.debug("appyGet().");
		return BASE_URL + "/apply_for_irba";
	}
}
