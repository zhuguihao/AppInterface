package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class ProductApplyQueryDto {

	private String barCode;
	private String applyStatus;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(barCode) || CommonUtil.isEmpty(applyStatus);
	}
	
	public boolean hasStatus() {
		return CommonUtil.isEmpty(applyStatus);
	}
}
