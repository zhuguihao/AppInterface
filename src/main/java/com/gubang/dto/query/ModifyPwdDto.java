package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class ModifyPwdDto {

	private String account;
	private String oldPassword;
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(account) || CommonUtil.isEmpty(oldPassword)||CommonUtil.isEmpty(password);
	}
}
