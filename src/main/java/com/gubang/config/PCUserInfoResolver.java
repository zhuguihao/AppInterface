package com.gubang.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.gubang.constant.Constant;
import com.gubang.entity.UserInfo;
import com.gubang.service.RedisService;

@Component
public class PCUserInfoResolver implements HandlerMethodArgumentResolver {

	@Resource
    private RedisService redisService;
	
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PCUserInfoParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = servletRequest.getHeader(Constant.TOKEN_HEADER_KEY);
        return redisService.get(Constant.PC_REDIS_USER_KEY, token, UserInfo.class);
    }
}
