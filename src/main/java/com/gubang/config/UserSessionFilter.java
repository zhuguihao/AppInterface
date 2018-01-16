//package com.gubang.config;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//import com.alibaba.fastjson.JSONObject;
//import com.gubang.constant.ResultCode;
//
//
///**
// * 
// * <p>
// * Description: 把没有登录的用户拦截的过滤器
// * </p>
// * <p>
// * Author: Administrator
// * </p>
// * <p>
// * Date: 2016年12月5日
// * </p>
// */
//@WebFilter(urlPatterns = "*")
//public class UserSessionFilter implements Filter {
//
//	private ServletContext context;
//
//	/** 某些服务不能拦截，比如说登录和注册，回调；如果不需要拦截的服务需要配在notFilter里边 */
//	private Set<String> notFilter = new HashSet<String>();
//
//	/**
//	 * 非登录时返回的报文
//	 */
//	private String notLoginString;
//
//	public void doFilter(ServletRequest request_, ServletResponse response_, FilterChain filterchain)
//			throws IOException, ServletException {
//		request_.setCharacterEncoding("utf-8");
//		response_.setContentType("application/json;charset=utf-8");
//		HttpServletResponse response = (HttpServletResponse) response_;
//		HttpServletRequest request = (HttpServletRequest) request_;
//		String requestURI = request.getRequestURI();
//		if (!notFilter.contains(requestURI)) {// 不在过滤器白名单里面
//			System.out.println("a");
//		}
//		filterchain.doFilter(request_, response_);
//	}
//
//	public void init(FilterConfig filterConfig) throws ServletException {
//		context = filterConfig.getServletContext();
//		JSONObject notLoginJson = new JSONObject();
//		notLoginJson.put("status", ResultCode.NOT_LOGIN.getCode());
//		notLoginJson.put("msg", ResultCode.NOT_LOGIN.getDesc());
//		notLoginString = notLoginJson.toString();
//
//		notFilter.add("/market/login/login");// 登录界面
//	}
//
//	public void destroy() {
//		context = null;
//	}
//}
