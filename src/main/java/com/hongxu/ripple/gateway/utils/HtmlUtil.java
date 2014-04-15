package com.hongxu.ripple.gateway.utils;

import java.io.BufferedReader;
import java.io.StringReader;

public class HtmlUtil {
	
	public static String getHtmlString(String str) {
		
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try{
			reader = new BufferedReader(
					new StringReader(str));
			String line = null;
			while((line = reader.readLine()) != null) {
				line = line.replace((char)12288, ' ');  //全角空格
				line = line.trim();
				System.out.println("|"+line+"|");
				if(line.length() > 0) {
					sb.append("<p>" + line + "</p>");
				}
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				reader.close();
			}catch(Exception ignore) {}
		}
		
		return sb.toString();
	}
}
