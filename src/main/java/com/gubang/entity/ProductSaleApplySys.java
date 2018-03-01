package com.gubang.entity;

import java.io.Serializable;

public class ProductSaleApplySys extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 2L;

	private String id;
	/**
	 * 申请表
	 */
	private String productSaleApplyId;
	/**
	 * 审批人
	 */
	private String applyUser;
	/**
	 * 回访内容
	 */
	private String applyDesc;
	/**
	 * 是否需要付费
	 */
	private String isPay;
	/**
	 * 付费的产品
	 */
	private String payGoods;
	/**
	 * 公司收件人
	 */
	private String expressName;
	/**
	 * 公司收件地址
	 */
	private String expressAddress;
	/**
	 * 公司收件人联系方式
	 */
	private String expressPhone;
	/**
	 * 审批驳回原因
	 */
	private String applyRejectResion;
	/**
	 * 审批维修状态（参考）
	 */
	private String applyPolicyState;
	/**
	 * 是否收件
	 */
	private String isRecipient;
	/**
	 * 售后部检查产品状态
	 */
	private String productStatus;
	/**
	 * 运单号
	 */
	private String waybillNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductSaleApplyId() {
		return productSaleApplyId;
	}

	public void setProductSaleApplyId(String productSaleApplyId) {
		this.productSaleApplyId = productSaleApplyId;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getPayGoods() {
		return payGoods;
	}

	public void setPayGoods(String payGoods) {
		this.payGoods = payGoods;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressAddress() {
		return expressAddress;
	}

	public void setExpressAddress(String expressAddress) {
		this.expressAddress = expressAddress;
	}

	public String getExpressPhone() {
		return expressPhone;
	}

	public void setExpressPhone(String expressPhone) {
		this.expressPhone = expressPhone;
	}

	public String getApplyRejectResion() {
		return applyRejectResion;
	}

	public void setApplyRejectResion(String applyRejectResion) {
		this.applyRejectResion = applyRejectResion;
	}

	public String getApplyPolicyState() {
		return applyPolicyState;
	}

	public void setApplyPolicyState(String applyPolicyState) {
		this.applyPolicyState = applyPolicyState;
	}

	public String getIsRecipient() {
		return isRecipient;
	}

	public void setIsRecipient(String isRecipient) {
		this.isRecipient = isRecipient;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

}