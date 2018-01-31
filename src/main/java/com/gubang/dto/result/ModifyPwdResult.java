package com.gubang.dto.result;

public class ModifyPwdResult {

	private String modifyResult;

	public String getModifyResult() {
		return modifyResult;
	}

	public void setModifyPwdResult(String modifyResult) {
		this.modifyResult = modifyResult;
	}

	public ModifyPwdResult setAccountNotExist() {
		modifyResult = "账号不存在";
		return this;
	}
	
	public ModifyPwdResult setOldPwdError() {
		modifyResult = "旧密码错误";
		return this;
	}

	public ModifyPwdResult setSuccess() {
		modifyResult = "修改成功";
		return this;
	}
}
