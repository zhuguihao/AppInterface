package com.gubang.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo {
	private String id;

	private String tSeriesId;

	private String proNameId;

	private String proModel;

	private String voltageRange;

	private BigDecimal distributionPrice;

	private BigDecimal retailPrice;

	private String proDesc;

	private Long policy;

	private Long policyReplace;

	private Long sort;

	private String delFlag;

	private String createBy;

	private Date createDate;

	private String updateBy;

	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String gettSeriesId() {
		return tSeriesId;
	}

	public void settSeriesId(String tSeriesId) {
		this.tSeriesId = tSeriesId;
	}

	public String getProNameId() {
		return proNameId;
	}

	public void setProNameId(String proNameId) {
		this.proNameId = proNameId == null ? null : proNameId.trim();
	}

	public String getProModel() {
		return proModel;
	}

	public void setProModel(String proModel) {
		this.proModel = proModel == null ? null : proModel.trim();
	}

	public String getVoltageRange() {
		return voltageRange;
	}

	public void setVoltageRange(String voltageRange) {
		this.voltageRange = voltageRange == null ? null : voltageRange.trim();
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
		this.proDesc = proDesc == null ? null : proDesc.trim();
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

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", proNameId=" + proNameId + ", proModel=" + proModel + ", voltageRange="
				+ voltageRange + ", distributionPrice=" + distributionPrice + ", retailPrice=" + retailPrice
				+ ", proDesc=" + proDesc + ", policy=" + policy + ", policyReplace=" + policyReplace + ", sort=" + sort
				+ ", delFlag=" + delFlag + ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + "]";
	}

}