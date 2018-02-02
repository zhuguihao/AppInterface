package com.gubang.vo;

import java.math.BigDecimal;

public class GetProductSaleVo {
	private String id;
	private String barCode;
	private String series;
	private String proName;
	private String proModel;
	private String voltageRange;
	private BigDecimal distributionPrice;
	private BigDecimal retailPrice;
	private String proDesc;
	private Long policy;
	private Long policyReplace;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public BigDecimal getDistributionPrice() {
		return distributionPrice;
	}

	public void setDistributionPrice(BigDecimal distributionPrice) {
		this.distributionPrice = distributionPrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public Long getPolicy() {
		return policy;
	}

	public void setPolicy(Long policy) {
		this.policy = policy;
	}

	public Long getPolicyReplace() {
		return policyReplace;
	}

	public void setPolicyReplace(Long policyReplace) {
		this.policyReplace = policyReplace;
	}

}
