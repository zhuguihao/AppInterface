package com.gubang.dto.apply;

import com.gubang.util.CommonUtil;

public class FirstTrialPassDto {
	private String productSaleApplyId;

	public String getProductSaleApplyId() {
		return productSaleApplyId;
	}

	public void setProductSaleApplyId(String productSaleApplyId) {
		this.productSaleApplyId = productSaleApplyId;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(productSaleApplyId);
	}
}