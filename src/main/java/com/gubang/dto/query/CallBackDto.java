package com.gubang.dto.query;

import com.gubang.constant.Constant;
import com.gubang.util.CommonUtil;

public class CallBackDto {
	/**
	 * 客户售后单ID
	 */
	private String productSaleApplyId;
	/**
	 * 电话回访记录
	 */
	private String applyDesc;
	/**
	 * 是否需要额外收费的产品
	 */
	private String isPay;
	/**
	 * 记录付费的产品信息
	 */
	private String payGoods;

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

	public boolean inValid() {
		if (Constant.IS_PAY_GOODS.equals(isPay)) {
			return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyDesc) || CommonUtil.isEmpty(isPay)
					|| CommonUtil.isEmpty(payGoods);
		}
		return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyDesc) || CommonUtil.isEmpty(isPay);
	}
}
