package com.common.dto;

import com.gubang.util.CommonUtil;

public class DictDto {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(code);
	}
}
