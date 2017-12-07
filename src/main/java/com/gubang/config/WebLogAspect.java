package com.gubang.config;

import java.util.Arrays;

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

@Aspect
@Component
public class WebLogAspect {
	
	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@Pointcut("execution(public * com.gubang.controller..*.*(..))")
	public void webLog(){}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable{
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		 // 记录下请求内容
        log.info("request url : " + request.getRequestURL().toString());
        log.info("class_method : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("input : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(returning = "ret" , pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable{
		log.info("output : " + ret);
	}
}
