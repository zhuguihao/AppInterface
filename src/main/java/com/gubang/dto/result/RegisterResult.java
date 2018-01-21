package com.gubang.dto.result;

public class RegisterResult {

	private String registerResult;

	public String getRegisterResult() {
		return registerResult;
	}

	public void setRegisterResult(String registerResult) {
		this.registerResult = registerResult;
	}
	
	public RegisterResult setAccountExist() {
		registerResult="账号存在";
		return this;
	}
	
	public RegisterResult setSuccess() {
		registerResult="注册成功";
		return this;
	}
}
