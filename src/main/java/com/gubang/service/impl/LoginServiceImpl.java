package com.gubang.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gubang.constant.Constant;
import com.gubang.dto.query.LoginQuery;
import com.gubang.dto.query.ModifyPwdDto;
import com.gubang.dto.query.RegisterQuery;
import com.gubang.dto.result.LoginResult;
import com.gubang.dto.result.ModifyPwdResult;
import com.gubang.dto.result.OutLoginResult;
import com.gubang.dto.result.RegisterResult;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.UserInfoMapper;
import com.gubang.service.LoginService;
import com.gubang.service.RedisService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserInfoMapper userInfoMapper;

	//@Autowired
	//UserSessionService userSessionService;
	@Autowired
	RedisService redisService;

	@Value(value="${redis.userinfo.timeout}")
	private Long timeout;
	
	@Override
	public ResultDTO register(RegisterQuery registerQuery) {
		// find by account; if accout exist return
		// insert into user
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

		// find by account,if not exist return
		UserInfo userEntity = userInfoMapper.findByAccount(loginQuery.getAccount());
		if (userEntity == null) {
			result.setLoginResult("账号不存在");
			return resultDTO.setFail(result);
		}

		// passwd compare with the md5 of passwd user input,if not exist return
		if (!userEntity.getPassword().equals(CommonUtil.md5(loginQuery.getPassword()))) {
			result.setLoginResult("密码错误");
			return resultDTO.setFail(result);
		}

		// use the token of the db record as key, remove data from redis (single
		// point login)
		redisService.remove(Constant.REDIS_USER_KEY, userEntity.getToken());

		// auth success,create uuid as token,now as last login date ,save to
		// user
		String token = CommonUtil.getUUid();
		Date lastLoginDate = new Date();
		UserInfo updateEntity = new UserInfo();
		updateEntity.setId(userEntity.getId());
		updateEntity.setLastLoginDate(lastLoginDate);
		updateEntity.setToken(token);
		userInfoMapper.updateByPrimaryKeySelective(updateEntity);

		// use the token just create as key,save userinfo to redis
		//userSessionService.set(token, userEntity);
		redisService.set(Constant.REDIS_USER_KEY, token, userEntity, timeout);
		// return token and userInfo
		result.setLoginResult("登录成功");
		result.setToken(token);
		result.setUserInfo(userEntity);
		result.getUserInfo().setToken(token);
		return resultDTO.setSuccess(result);
	}

	@Override
	public ResultDTO logout(UserInfo user) {
		ResultDTO resultDTO = new ResultDTO();
		OutLoginResult outLoginResult = new OutLoginResult();
		// 清空数据库token
		UserInfo updateEntity = new UserInfo();
		updateEntity.setId(user.getId());
		updateEntity.setToken("");
		userInfoMapper.updateByPrimaryKeySelective(updateEntity);
		redisService.remove(Constant.TOKEN_HEADER_KEY, user.getToken());
		outLoginResult.setOutLoginResult("退出成功");
		return resultDTO.setSuccess(outLoginResult);
	}

	@Override
	public ResultDTO modifyPwd(ModifyPwdDto params) {
		ResultDTO resultDTO = new ResultDTO();
		ModifyPwdResult result = new ModifyPwdResult();

		// find by account,if not exist return
		UserInfo userEntity = userInfoMapper.findByAccount(params.getAccount());
		if (userEntity == null) {
			result.setAccountNotExist();
			return resultDTO.setFail(result);
		}
		
		/**
		 * 判断是否是注册时的微信号
		 */
		if(!userEntity.getOpenId().equals(params.getOpenId())){
			result.setOldPwdError();
			return resultDTO.setFail(result);
		}

		UserInfo updateEntity = new UserInfo();
		updateEntity.setId(userEntity.getId());
		updateEntity.setPassword(CommonUtil.md5(params.getPassword()));
		updateEntity.setUpdateBy(updateEntity.getId());
		updateEntity.setUpdateDate(new Date());
		userInfoMapper.updateByPrimaryKeySelective(updateEntity);

		result.setModifyPwdResult("修改成功");
		return resultDTO.setSuccess(result);
	}
}
