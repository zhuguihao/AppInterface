package com.gubang.dto.apply;

import com.gubang.util.CommonUtil;

public class ApplyDto {
	/**
	 * 客户姓名
	 */
	private String cusName;
	/**
	 * 客户手机号
	 */
	private String cusTelphone;
	/**
	 * 故障点
	 */
	private String faultPoint;
	private String barCode;

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusTelphone() {
		return cusTelphone;
	}

	public void setCusTelphone(String cusTelphone) {
		this.cusTelphone = cusTelphone;
	}

	public String getFaultPoint() {
		return faultPoint;
	}

	public void setFaultPoint(String faultPoint) {
		this.faultPoint = faultPoint;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(cusName) || CommonUtil.isEmpty(cusTelphone) || CommonUtil.isEmpty(faultPoint)
				|| CommonUtil.isEmpty(barCode);
	}
}