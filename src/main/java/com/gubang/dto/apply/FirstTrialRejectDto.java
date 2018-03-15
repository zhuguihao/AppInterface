package com.gubang.dto.apply;

import com.gubang.util.CommonUtil;

public class FirstTrialRejectDto {
	private String productSaleApplyId;
	private String applyRejectResion;

	public String getProductSaleApplyId() {
		return productSaleApplyId;
	}

	public void setProductSaleApplyId(String productSaleApplyId) {
		this.productSaleApplyId = productSaleApplyId;
	}

	public String getApplyRejectResion() {
		return applyRejectResion;
	}

	public void setApplyRejectResion(String applyRejectResion) {
		this.applyRejectResion = applyRejectResion;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(productSaleApplyId) || CommonUtil.isEmpty(applyRejectResion);
	}
}