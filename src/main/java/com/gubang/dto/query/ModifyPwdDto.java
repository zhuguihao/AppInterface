package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class ModifyPwdDto {

	private String account;
	private String openId;
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(account) || CommonUtil.isEmpty(openId) || CommonUtil.isEmpty(password);
	}
}
