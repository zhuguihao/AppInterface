package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

public class ProductSaleDelDto {

	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(id);
	}
}
