package com.hongxu.ripple.gateway.utils;

import java.security.MessageDigest;

public class MD5Util {
	
	public static String digest(String plainText ) throws Exception { 
		
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		md.update(plainText.getBytes()); 
		byte b[] = md.digest(); 

		int i; 

		StringBuffer buf = new StringBuffer(""); 
		for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0) i+= 256; 
			if(i<16) 
				buf.append("0"); 
			buf.append(Integer.toHexString(i)); 
		}
		
		return buf.toString();
		
	}
	
	public static void main(String[] args) throws Exception {
		
		String msg = MD5Util.digest("caizhihongtest");
		
		System.out.println(msg);
	}
}
