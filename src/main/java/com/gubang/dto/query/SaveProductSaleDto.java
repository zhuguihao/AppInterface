package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class SaveProductSaleDto {

	private String productId;
	private String barCode;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(productId) || CommonUtil.isEmpty(barCode);
	}
}
