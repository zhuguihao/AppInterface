package com.gubang.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gubang.constant.Constant;
import com.gubang.entity.UserInfo;
import com.gubang.service.UserCacheService;
import com.gubang.util.CommonUtil;

@Service
public class UserCacheServiceImpl implements UserCacheService,InitializingBean {

	@Resource
    private RedisTemplate<String, UserInfo> redisTemplate;

	@Value(value="${redis.userinfo.timeout}")
	private Integer timeout;
	
	/**
	 * 新增记录（如果存在则覆盖）
	 * info.imei为键
	 * info为值
	 * @param info
	 */
	@Override
	public void set(String key, UserInfo info) {
		redisTemplate.opsForHash().put(Constant.REDIS_USER_KEY, key, info);
	}
	
	/**
	 * 查询imei列表对应的记录
	 * info.imei为键
	 * info为值
	 * @param info
	 */
	@Override
	public void remove(String key) {
		if (!CommonUtil.isEmpty(key)) {
			redisTemplate.opsForHash().delete(Constant.REDIS_USER_KEY, key);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		redisTemplate.expire(Constant.REDIS_USER_KEY, timeout, TimeUnit.SECONDS);
	}
}