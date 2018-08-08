package com.common.dto;

import com.gubang.util.CommonUtil;

public class LoginDto {
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 查看菜单的类型
	 */
	private String type;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(account) || CommonUtil.isEmpty(pwd) || CommonUtil.isEmpty(type);
	}
}
