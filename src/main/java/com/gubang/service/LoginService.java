package com.gubang.service;

import com.gubang.dto.query.LoginQuery;
import com.gubang.dto.query.RegisterQuery;
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
}