//package com.gubang.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.gubang.constant.Constant;
//import com.gubang.entity.User;
//
//public class AppUtil {
//
//	public static User getUserByRequest(HttpServletRequest request) {
//		return CacheUtil.get(request.getHeader(Constant.TOKEN_HEADER_KEY), User.class);
//	}
//	
//	/**
//	 * @param beginPage 1开始
//	 * @param pageSize 
//	 * @return
//	 */
//	public static Map<String, Object> getCommonPageQueryMap(int beginPage, int pageSize) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("offset", (beginPage - 1) * pageSize);
//		map.put("limit", pageSize);
//		return map;
//	}
//}
