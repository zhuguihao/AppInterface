package com.gubang.dto.result;

public class SoldProductResult {
	private String id;
	private String barCode;
	private String afterSaleTime;
	private String series;
	private String proName;
	private String proModel;
	private String voltageRange;
	private String proDesc;
	private Integer policy;
	private Integer policyReplace;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getAfterSaleTime() {
		return afterSaleTime;
	}

	public void setAfterSaleTime(String afterSaleTime) {
		this.afterSaleTime = afterSaleTime;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProModel() {
		return proModel;
	}

	public void setProModel(String proModel) {
		this.proModel = proModel;
	}

	public String getVoltageRange() {
		return voltageRange;
	}

	public void setVoltageRange(String voltageRange) {
		this.voltageRange = voltageRange;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public Integer getPolicy() {
		return policy;
	}

	public void setPolicy(Integer policy) {
		this.policy = policy;
	}

	public Integer getPolicyReplace() {
		return policyReplace;
	}

	public void setPolicyReplace(Integer policyReplace) {
		this.policyReplace = policyReplace;
	}

}
