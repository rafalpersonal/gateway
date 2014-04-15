package com.hongxu.ripple.gateway.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDGenerator {
	
	private static int count = 0;
	
	public synchronized static String getId(){
		return generateId("yyyyMMddHHmmss");
	}
	
	private synchronized static String generateId(String formatStr){
		if(count>999){
			count = 0;
		}
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		String id = df.format(new Date());
		id = id.substring(2);
		if(count < 10) {
			id += "00" + count;
		}else if(count < 100 && count >= 10) {
			id += "0" + count;
		}else {
			id += count;
		}
		
		count++;
		return id;
	}
}