package com.gubang.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

/**
 * 通用工具类
 * 
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

	public static String structString(String sep, String... fields) {
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
	 * 
	 * @param end
	 * @return
	 */
	static public int getRamdon(int end) {
		Random ra = new Random(System.currentTimeMillis());
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

	public static String md5(String source) {

		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(source.getBytes("utf-8"));

			for (int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}

	public static Date getAnotherDate(Date date, int addDay) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addDay);
		date = calendar.getTime();
		return date;
	}

	public static Date parseDate(SimpleDateFormat myFmt2, String dateStr) {
		try {
			return myFmt2.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getNowFormat() {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Integer parseInt(String dateStr) {
		try {
			return Integer.parseInt(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
