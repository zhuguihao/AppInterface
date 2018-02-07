package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class SoldDto {

	private String barCode;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(barCode);
	}
}
