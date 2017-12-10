package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class GetProductInfoByBarcodeQuery {

	private String barcode;

	public String getBarcode() {
		return barcode;
	}



	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(barcode);
	}
}
