package com.hongxu.ripple.gateway.style.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class Adapter {
	
	protected String unhandlePage(String styleType, Model model) {
		
		List<String> messageList = new ArrayList<String>();
		messageList.add( "Adapter can't handle styleType '" + styleType+ "' !");
		model.addAttribute("messageList", messageList);
		return "error";
	}
}
