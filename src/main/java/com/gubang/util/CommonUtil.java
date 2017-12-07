package com.gubang.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

/**
 * 通用工具类
 * @author liangwenhan
 *
 */
public class CommonUtil {

	public static String getStackTrace(Throwable exception) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			return sw.toString();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	public static String structString(String sep, Collection<?> coll) {
		StringBuffer buff = new StringBuffer();
		for (Object object : coll) {
			if (object != null) {
				buff.append(object.toString()).append(sep);
			}
		}
		if (buff.length() > 0) {
			buff.setLength(buff.length() - 1);
		}
		return buff.toString();
	}
		
	public static String structString(String sep,String...fields) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			if (fields.length - 1 == i) {
				buff.append(fields[i]);
			} else {
				buff.append(fields[i]).append(sep);
			}
		}
		return buff.toString();
	}
	
	/**
	 * 获得一个从0到end之间的整形，包括0，但不包括end
	 * @param end
	 * @return
	 */
	static public int getRamdon(int end) {
		Random ra =new Random(System.currentTimeMillis());
		return ra.nextInt(end);
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	public static String getUUid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static Integer parseInteger(String intString) {
		try {
			return Integer.parseInt(intString);
		} catch (Exception e) {
		}
		return null;
	}
	
}
