package com.gubang.dto.apply;

import com.gubang.util.CommonUtil;

public class SignExpressDto {
	private String productSaleApplyId;
	private String partsList;

	public String getProductSaleApplyId() {
		return productSaleApplyId;
	}

	public void setProductSaleApplyId(String productSaleApplyId) {
		this.productSaleApplyId = productSaleApplyId;
	}

	public String getPartsList() {
		return partsList;
	}

	public void setPartsList(String partsList) {
		this.partsList = partsList;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(productSaleApplyId);
	}
}