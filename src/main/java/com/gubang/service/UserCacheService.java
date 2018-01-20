package com.gubang.service;

import com.gubang.entity.UserInfo;

public interface UserCacheService {
	public void set(String key, UserInfo info);
	public void remove(String key);
}