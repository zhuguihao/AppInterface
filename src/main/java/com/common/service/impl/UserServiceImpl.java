package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.common.dto.GetGroupUserDto;
import com.common.dto.LoginDto;
import com.common.dto.LoginResult;
import com.common.service.UserService;
import com.gubang.constant.Constant;
import com.gubang.entity.GroupUserCenter;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.MenuMapper;
import com.gubang.mapper.UserGroupMapper;
import com.gubang.mapper.UserInfoMapper;
import com.gubang.service.RedisService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import com.gubang.vo.GroupUserVo;
import com.gubang.vo.MenuVo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	RedisService redisService;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private UserGroupMapper userGroupMapper;
	@Value(value = "${redis.userinfo.timeout}")
	private Long timeout;

	@Override
	public ResultDTO getUser(UserInfo userInfo, UserInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			List<UserInfo> getUser = userInfoMapper.getUser(params);

			return result.setSuccess(getUser);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getGroupUser(UserInfo userInfo, GetGroupUserDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			if (null == userInfo) {
				return result.setNotLogin();
			}

			GroupUserVo groupUserVo = new GroupUserVo();

			UserInfo user = new UserInfo();
			user.setAccount(params.getAccount());
			user.setNickName(params.getNickName());
			groupUserVo.setUserList(userInfoMapper.getUser(user));

			GroupUserCenter groupUserCenter = new GroupUserCenter();
			groupUserCenter.settSysGroupId(params.getRoleId());
			groupUserCenter.settSysUserId(params.getUserId());
			groupUserVo.setCheckedList(userGroupMapper.getGroupUserIds(groupUserCenter));

			return result.setSuccess(groupUserVo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO editUser(UserInfo userInfo, UserInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			userInfoMapper.updateByPrimaryKeySelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addUser(UserInfo userInfo, UserInfo params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			params.setId(CommonUtil.getUUid());
			params.setCreateBy(userInfo.getId());
			params.setCreateDate(new Date());
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	@Transactional
	public ResultDTO login(LoginDto params) {
		ResultDTO result = new ResultDTO();
		LoginResult loginInfo = new LoginResult();
		try {
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			/**
			 * 1.验证账号是否存在,判断是否是有后台权限的用户 2.校验是否存在角色ID，不存在则需要联系管理员 3.校验验证码是否正确
			 * 4.验证密码是否正确 5.查询当前用户的菜单信息 6.成功后将客户信息插入缓存中
			 */
			UserInfo userEntity = userInfoMapper.findByAccount(params.getAccount());
			if (userEntity == null || StringUtils.isEmpty(userEntity.getGroupId())) {
				return result.setAccountError();
			}

			if (!userEntity.getPassword().equals(CommonUtil.md5(params.getPwd()))) {
				return result.setPwdError();
			}
			
			MenuVo menu = new MenuVo();
			menu.setType(params.getType());
			menu.setUserId(userEntity.getId());

			redisService.remove(Constant.REDIS_USER_KEY, userEntity.getToken());

			String token = CommonUtil.getUUid();
			Date lastLoginDate = new Date();
			UserInfo updateEntity = new UserInfo();
			updateEntity.setId(userEntity.getId());
			updateEntity.setLastLoginDate(lastLoginDate);
//			updateEntity.setToken(token);
			userInfoMapper.updateByPrimaryKeySelective(updateEntity);

			redisService.set(Constant.REDIS_USER_KEY, token, userEntity, timeout);

			loginInfo.setLoginResult("登录成功");
			loginInfo.setToken(token);
			loginInfo.setUserInfo(userEntity);
			loginInfo.getUserInfo().setToken(token);
			loginInfo.setMenuList(CommonUtil.formatMenu(menuMapper.select(menu)));

			return result.setSuccess(loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
}