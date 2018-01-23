package com.gubang.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gubang.service.RedisService;
import com.gubang.util.CommonUtil;

@Service
public class RedisServiceImpl implements RedisService {

	@Resource
    private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void set(String key, String hashkey, Object info, Long timeoutSecond) {
		redisTemplate.opsForHash().put(key, hashkey, info);
		if (timeoutSecond != null) {
			redisTemplate.expire(key, timeoutSecond, TimeUnit.SECONDS);
		}
	}

	@Override
	public <T> T get(String key, String hashkey, Class<T> c) {
		if(null == hashkey){
			return null;
		}
		Object o = redisTemplate.opsForHash().get(key, hashkey);
		if (o == null) {
			return null;
		} else {
			return c.cast(o);
		}
	}

	@Override
	public void remove(String key, String hashkey) {
		if (!CommonUtil.isEmpty(hashkey)) {
			redisTemplate.opsForHash().delete(key, hashkey);
		}
	}
}
