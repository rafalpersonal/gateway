package com.hongxu.ripple.gateway.web.manager;
/**
 * MSubledgerController
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongxu.ripple.gateway.federation.subledger.pnp.FederationSubledger;
import com.hongxu.ripple.gateway.federation.subledger.pnp.to.Lines;

@Controller
@RequestMapping(value = MSubledgerController.BASE_URL)
public class MSubledgerController  extends MBaseController{

	private static Logger logger = LoggerFactory.getLogger(MSubledgerController.class);
	
	
	
	@RequestMapping(value = "/subledger_list.r", method = {RequestMethod.GET,RequestMethod.POST})
	public String subledgerList(
													Model model
													) {
		logger.debug("subledgerList().");
		try{
			FederationSubledger fs = new FederationSubledger();
			Lines lines = fs.getUserTransactionHistory("user1@yourapp.com");
			model.addAttribute("list", lines.getLines());
		}catch(Exception ex){
			logger.error("Find user transaction history error:",ex);
		}
		
		return BASE_URL+"/subledger_list";
	}
}
