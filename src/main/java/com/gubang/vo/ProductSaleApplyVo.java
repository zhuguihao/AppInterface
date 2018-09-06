package com.gubang.vo;

import java.io.Serializable;
import com.alibaba.fastjson.JSONArray;
import com.gubang.entity.CommonEntity;

public class ProductSaleApplyVo extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String cusName;
	private String cusTelphone;
	private String faultPoint;
	private String applyStatus;
	private String address;
	private String addressee;
	private String addressPhone;
	private String waybillNumber;
	private String productId;
	private String customerId;
	private String barCode;
	private String productStatus;
	private String afterSaleTime;
	private String saleMan;
	private String series;
	private String proName;
	private String proModel;
	private String voltageRange;
	private String distributionPrice;
	private String retailPrice;
	private String proDesc;
	private Long policy;
	private Long policyReplace;

	private String applyUser;
	private String applyDesc;
	private String isPay;
	private String payGoods;
	private String expressName;
	private String expressAddress;
	private String expressPhone;
	private String applyRejectResion;
	private String applyPolicyState;
	private String isRecipient;
	/**
	 * 售后判断维修状态
	 */
	private String sysProductStatus;
	private String sysWaybillNumber;
	/**
	 * 返回前端添加状态值描述
	 */
	private String applyStatusDesc;
	/**
	 * 故障图列表
	 */
	private JSONArray file;
	/**
	 * 配件列表
	 */
	private String partsList;
	/**
	 * 是否直寄快递
	 */
	private String isMailingAccessories;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getAfterSaleTime() {
		return afterSaleTime;
	}

	public void setAfterSaleTime(String afterSaleTime) {
		this.afterSaleTime = afterSaleTime;
	}

	public String getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
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

	public String getDistributionPrice() {
		return distributionPrice;
	}

	public void setDistributionPrice(String distributionPrice) {
		this.distributionPrice = distributionPrice;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
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

	public String getSysProductStatus() {
		return sysProductStatus;
	}

	public void setSysProductStatus(String sysProductStatus) {
		this.sysProductStatus = sysProductStatus;
	}

	public String getSysWaybillNumber() {
		return sysWaybillNumber;
	}

	public void setSysWaybillNumber(String sysWaybillNumber) {
		this.sysWaybillNumber = sysWaybillNumber;
	}

	public String getApplyStatusDesc() {
		return applyStatusDesc;
	}

	public void setApplyStatusDesc(String applyStatusDesc) {
		this.applyStatusDesc = applyStatusDesc;
	}

	public JSONArray getFile() {
		return file;
	}

	public void setFile(JSONArray file) {
		this.file = file;
	}

	public String getPartsList() {
		return partsList;
	}

	public void setPartsList(String partsList) {
		this.partsList = partsList;
	}

	public String getIsMailingAccessories() {
		return isMailingAccessories;
	}

	public void setIsMailingAccessories(String isMailingAccessories) {
		this.isMailingAccessories = isMailingAccessories;
	}

}
