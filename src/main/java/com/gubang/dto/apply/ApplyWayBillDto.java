package com.gubang.dto.apply;

import java.io.Serializable;

import com.gubang.util.CommonUtil;

public class ApplyWayBillDto implements Serializable {

	private static final long serialVersionUID = 5L;

	/**
	 * 售后单ID
	 */
	private String productSaleApplyId;
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

	public String getProductSaleApplyId() {
		return productSaleApplyId;
	}

	public void setProductSaleApplyId(String productSaleApplyId) {
		this.productSaleApplyId = productSaleApplyId;
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

	public boolean inValid() {
		return CommonUtil.isEmpty(addressee) || CommonUtil.isEmpty(address) || CommonUtil.isEmpty(addressPhone)
				|| CommonUtil.isEmpty(waybillNumber);
	}

}