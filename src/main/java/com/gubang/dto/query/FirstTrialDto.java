package com.gubang.dto.query;

import java.io.Serializable;

import com.gubang.util.CommonUtil;

public class FirstTrialDto implements Serializable {

	private static final long serialVersionUID = 3L;

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
	 * 审批驳回原因
	 */
	private String applyRejectResion;
	/**
	 * 售后部检查产品状态
	 */
	private String productStatus;

	public boolean inValid() {
		if(null == isPay){
			return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyDesc) || CommonUtil.isEmpty(applyRejectResion)
					|| CommonUtil.isEmpty(productStatus);
		}
		return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyDesc) || CommonUtil.isEmpty(isPay)
				|| CommonUtil.isEmpty(payGoods) || CommonUtil.isEmpty(applyRejectResion)
				|| CommonUtil.isEmpty(productStatus);
	}

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

	public String getApplyRejectResion() {
		return applyRejectResion;
	}

	public void setApplyRejectResion(String applyRejectResion) {
		this.applyRejectResion = applyRejectResion;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

}