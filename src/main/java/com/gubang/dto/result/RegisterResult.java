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
		registerResult="account exist.";
		return this;
	}
	
	public RegisterResult setSuccess() {
		registerResult="ok.";
		return this;
	}
}
