package com.hongxu.ripple.gateway.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * SN生成工具
 * 
 * @author 王欣
 * 
 */
public class SnUtils {

	public static final SimpleDateFormat SDF_SN_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final SimpleDateFormat SDF_SN_SERIAL_KEY = new SimpleDateFormat(
			"yyyyMMdd");
	public static final DecimalFormat DF = new DecimalFormat("0000");
	public static Hashtable<String, Integer> TODAY_SERIAL = new Hashtable<String, Integer>();

	public static String genOrderSn() {
		Date now = new Date();
		if (TODAY_SERIAL.get(SDF_SN_SERIAL_KEY.format(now)) == null) {
			TODAY_SERIAL.clear();
			TODAY_SERIAL.put(SDF_SN_SERIAL_KEY.format(now), 1);
		} else {
			TODAY_SERIAL.put(SDF_SN_SERIAL_KEY.format(now),
					TODAY_SERIAL.get(SDF_SN_SERIAL_KEY.format(now)) + 1);
		}
		return SDF_SN_FORMAT.format(new Date())
				+ DF.format(TODAY_SERIAL.get(
						SDF_SN_SERIAL_KEY.format(now)).longValue());
	}
	
}
