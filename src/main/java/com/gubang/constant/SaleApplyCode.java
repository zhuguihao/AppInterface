package com.gubang.constant;

/**
 * 售后申请单状态
 * 
 * @author pc
 * 
 */
public enum SaleApplyCode {

	AFTER_SALE_LIST("after_sale_list", "售后单"),
	FIRST_TRIAL("first_trial", "售后人员初审");
	
	private SaleApplyCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private String code;  
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}