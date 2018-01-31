package com.gubang.config;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;

@Aspect
@Component
public class WebLogAspect {

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Pointcut("execution(public * com.gubang.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		log.info("request url : " + request.getRequestURL().toString());
		log.info("request IP : " + request.getRemoteAddr());
		log.info("NGINX转发前 IP : " + request.getHeader("service_ip"));
		// 获取传入目标方法的参数
		Object[] args = joinPoint.getArgs();
		if(args.length>2){
			log.info("入参为" + JSONArray.toJSONString(args[2]));
		}
		

	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		log.info("出参为" + JSONArray.toJSONString(ret));
	}
}
