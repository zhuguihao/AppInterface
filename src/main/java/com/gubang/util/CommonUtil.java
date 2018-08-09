package com.gubang.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.gubang.entity.Group;
import com.gubang.entity.Menu;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 通用工具类
 * 
 * @author liangwenhan
 *
 */
@SuppressWarnings({ "unused", "restriction" })
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

	public static Date addDate(Date date, int addDay, String type) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		switch (type) {
		case "YEAR":
			calendar.add(Calendar.YEAR, addDay);
			break;
		case "MONTH":
			calendar.add(Calendar.MONTH, addDay);
			break;
		case "DAY":
			calendar.add(Calendar.DATE, addDay);
			break;
		default:
			break;
		}
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

	public static String getFormatDate(Date date, String fromat) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(fromat);
			return dateFormat.format(date);
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

	public static String Encoder(byte[] bytes) {
		// TODO Auto-generated method stub
		return new BASE64Encoder().encode(bytes);
	}
	
	public static <T extends Menu> List<T> formatMenu(List<T> list) {
		List<T> nodeList = new ArrayList<T>();
		for (T node1 : list) {
			boolean mark = false;
			for (T node2 : list) {
				if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
					mark = true;
					if (node2.getChildren() == null) {
						node2.setChildren(new ArrayList<Menu>());
					}
					if (node1.getChildren() == null) {
						node1.setChildren(new ArrayList<Menu>());
					}
					node2.getChildren().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		return nodeList;
	}

	public static <T extends Menu> List<T> formatMenuTree(List<T> list, String parentId) {
		List<T> nodeList = new ArrayList<T>();
		for (T node1 : list) {
			boolean mark = false;
			for (T node2 : list) {
				if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
					mark = true;
					if (node2.getChildren() == null) {
						node2.setChildren(new ArrayList<Menu>());
					}
					if (node1.getChildren() == null) {
						node1.setChildren(new ArrayList<Menu>());
					}
					node2.getChildren().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		return getParentMenuTree(nodeList,new ArrayList<T>(),parentId);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Menu> List<T> getParentMenuTree(List<T> list, ArrayList<T> nodeList,String parentId) {
		for (T node1 : list) {
			if(parentId.equals(node1.getId())){
				nodeList.add(node1);
				break;
			}

			if(node1.getChildren() != null && node1.getChildren().size()>0){
				getParentMenuTree((List<T>) node1.getChildren(),nodeList,parentId);
			}
		}

		return nodeList;
	}
	
	public static <T extends Group> List<T> formatTree(List<T> list, String parentId) {
		List<T> nodeList = new ArrayList<T>();
		for (T node1 : list) {
			boolean mark = false;
			for (T node2 : list) {
				if (node1.getParentId() != null && node1.getParentId().equals(node2.getId())) {
					mark = true;
					if (node2.getChildren() == null) {
						node2.setChildren(new ArrayList<Group>());
					}
					if (node1.getChildren() == null) {
						node1.setChildren(new ArrayList<Group>());
					}
					node2.getChildren().add(node1);
					break;
				}
			}
			if (!mark) {
				nodeList.add(node1);
			}
		}
		return getParentTree(nodeList,new ArrayList<T>(),parentId);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Group> List<T> getParentTree(List<T> list, ArrayList<T> nodeList,String parentId) {
		for (T node1 : list) {
			if(parentId.equals(node1.getId())){
				nodeList.add(node1);
				break;
			}

			if(node1.getChildren() != null && node1.getChildren().size()>0){
				getParentTree((List<T>) node1.getChildren(),nodeList,parentId);
			}
		}

		return nodeList;
	}

}
