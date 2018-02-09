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
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	private String is_del;

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

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getIs_del() {
		return is_del;
	}

	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}

}