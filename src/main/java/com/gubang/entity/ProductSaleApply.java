package com.gubang.entity;

import java.util.Date;

public class ProductSaleApply {
	private String id;
	/**
	 * 出售产品ID
	 */
	private String productSaleId;
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
	/**
	 * 订单状态
	 */
	private String applyStatus;
	/**
	 * 寄件人信息
	 */
	private String addressee;
	/**
	 * 寄件人联系地址
	 */
	private String address;
	/**
	 * 寄件人联系方式
	 */
	private String addressPhone;
	/**
	 * 运单号
	 */
	private String waybillNumber;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String isDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductSaleId() {
		return productSaleId;
	}

	public void setProductSaleId(String productSaleId) {
		this.productSaleId = productSaleId;
	}

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

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}