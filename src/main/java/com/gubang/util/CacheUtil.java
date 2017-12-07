//package com.gubang.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.gubang.entity.User;
//
//public class CacheUtil {
//
//	final static private Map<String, Object> cache = new HashMap<String, Object>();
//	
//	public static <T> T get(String key, Class<T> type) {
//		Object obj = cache.get(key);
//		if (obj != null) {
//			return type.cast(obj);
//		} else {
//			return null;
//		}
//	}
//	
//	public static <T> T put(String key, Object obj, Class<T> type) {
//		Object pre = cache.put(key, obj);
//		if (pre != null) {
//			return type.cast(pre);
//		} else {
//			return null;
//		}
//	}
//	
//	public static void put(String key, Object obj) {
//		cache.put(key, obj);
//	}
//	
//	public static void remove(String key) {
//		cache.remove(key);
//	}
//	
//	public static void main(String args[]) {
//		User us = new User();
//		us.setAccount("account");
//		put("a", us);
//		
//		User sr = get("a", User.class);
//		sr = put("a", us, User.class);
//		System.out.println(sr);
//	}
//}
