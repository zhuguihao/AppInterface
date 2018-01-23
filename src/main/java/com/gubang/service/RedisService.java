package com.gubang.service;

public interface RedisService {
	public void set(String key, String hashkey, Object info, Long timeoutSecond);
	public <T> T get(String key, String hashkey, Class<T> c);
	public void remove(String key, String hashkey);
}