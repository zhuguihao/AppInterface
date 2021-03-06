package com.gubang.dto.query;

import java.io.Serializable;

import com.alibaba.druid.util.StringUtils;
import com.gubang.constant.Constant;
import com.gubang.util.CommonUtil;

public class FirstTrialDto implements Serializable {
	private static final long serialVersionUID = 6399850814827963466L;
	private String productSaleApplyId;
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
	 * 售后部检查产品状态
	 */
	private String productStatus;
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
	 * 初审审批的维修状态（参考）
	 */
	private String applyPolicyState;
	/**
	 * 是否直接寄配件，将会绕过维修部
	 */
	private String isMailingAccessories;

	public String getProductSaleApplyId() {
		return productSaleApplyId;
	}

	public void setProductSaleApplyId(String productSaleApplyId) {
		this.productSaleApplyId = productSaleApplyId;
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

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
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

	public String getApplyPolicyState() {
		return applyPolicyState;
	}

	public void setApplyPolicyState(String applyPolicyState) {
		this.applyPolicyState = applyPolicyState;
	}

	public String getIsMailingAccessories() {
		return isMailingAccessories;
	}

	public void setIsMailingAccessories(String isMailingAccessories) {
		this.isMailingAccessories = isMailingAccessories;
	}

	public boolean inValid() {
		if (StringUtils.isEmpty(isPay) || Constant.IS_NO_PAY_GOODS.equals(isPay)) {
			return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyDesc) || CommonUtil.isEmpty(isMailingAccessories);
		}
		return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyDesc) || CommonUtil.isEmpty(isPay)
				|| CommonUtil.isEmpty(payGoods) || CommonUtil.isEmpty(isMailingAccessories);
	}
}