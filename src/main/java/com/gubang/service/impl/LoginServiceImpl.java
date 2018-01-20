package com.gubang.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gubang.constant.Constant;
import com.gubang.dto.query.LoginQuery;
import com.gubang.dto.query.RegisterQuery;
import com.gubang.dto.result.LoginResult;
import com.gubang.dto.result.RegisterResult;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.UserInfoMapper;
import com.gubang.service.LoginService;
import com.gubang.service.UserCacheService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	UserCacheService userCacheService;
	
	@Override
	public ResultDTO register(RegisterQuery registerQuery) {
		//find by account; if accout exist return
		//insert into user
		ResultDTO resultDTO = new ResultDTO();
		RegisterResult result = new RegisterResult();
		UserInfo userExistAlready = userInfoMapper.findByAccount(registerQuery.getAccount());
		if (userExistAlready != null) {
			return resultDTO.setFail(result.setAccountExist());
		}
		
		UserInfo newComeUserInfo = new UserInfo();
		BeanUtils.copyProperties(registerQuery, newComeUserInfo);
		newComeUserInfo.setPassword(CommonUtil.md5(registerQuery.getPassword()));
		newComeUserInfo.setId(CommonUtil.getUUid());
		newComeUserInfo.setCreateBy(newComeUserInfo.getId());
		newComeUserInfo.setCreateDate(new Date());
		newComeUserInfo.setIsDel(Constant.DB_FALSE_FLAG);
		newComeUserInfo.setIsPass(Constant.DB_FALSE_FLAG);
		newComeUserInfo.setUpdateBy(newComeUserInfo.getId());
		newComeUserInfo.setUpdateDate(new Date());
		int effectLine = userInfoMapper.insert(newComeUserInfo);
		if (effectLine <= 0) {
			return resultDTO.setSystemError();
		}
		return resultDTO.setSuccess(result.setSuccess());
	}

	@Override
	public ResultDTO login(LoginQuery loginQuery) {
		
		ResultDTO resultDTO = new ResultDTO();
		LoginResult result = new LoginResult();
		
		//find by account,if not exist return
		UserInfo userEntity = userInfoMapper.findByAccount(loginQuery.getAccount());
		if (userEntity == null) {
			 result.setLoginResult("account not exist.");
			 return resultDTO.setFail(result);
		}
		
		//passwd compare with the md5 of passwd user input,if not exist return
		if (!userEntity.getPassword().equals(CommonUtil.md5(loginQuery.getPassword()))) {
			result.setLoginResult("password incorrect.");
			 return resultDTO.setFail(result);
		}
		
		//use the token of the db record as key, remove data from redis (single point login)
		userCacheService.remove(userEntity.getToken());
		
		//auth success,create uuid as token,now as last login date ,save to user
		String token = CommonUtil.getUUid();
		Date lastLoginDate = new Date();
		UserInfo updateEntity = new UserInfo();
		updateEntity.setId(userEntity.getId());
		updateEntity.setLastLoginDate(lastLoginDate);
		updateEntity.setToken(token);
		userInfoMapper.updateByPrimaryKeySelective(updateEntity);
		
		//use the token just create as key,save userinfo to redis
		userCacheService.set(token, userEntity);
		
		//return token and userInfo
		result.setLoginResult("ok.");
		result.setToken(token);
		result.setUserInfo(userEntity);
		result.getUserInfo().setToken(token);
		return resultDTO.setSuccess(result);
	}
}
