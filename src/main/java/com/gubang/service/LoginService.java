package com.gubang.service;

import com.gubang.dto.query.LoginQuery;
import com.gubang.dto.query.ModifyPwdDto;
import com.gubang.dto.query.RegisterQuery;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface LoginService {

	/**
	 * if success，return null，else return reason
	 * @return
	 */
	ResultDTO register(RegisterQuery registerQuery);
	
	/**
	 * @return if login success
	 */
	ResultDTO login(LoginQuery loginQuery);
	
	/**
	 * 退出登录
	 * @param user
	 * @return
	 */
	ResultDTO outLogin(UserInfo user);
	
	/**
	 * 修改密码
	 * @param params
	 * @return
	 */
	ResultDTO modifyPwd(ModifyPwdDto params);
}
