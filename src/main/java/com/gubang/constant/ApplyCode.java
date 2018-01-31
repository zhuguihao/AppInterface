package com.gubang.constant;

/**
 * 申请状态码值
 * 
 * @author pc
 * 
 */
public enum ApplyCode {

	APPLY_STORAGE("apply_storage", "入库"),
	APPLY_OUT_STORAGE("apply_out_storage","出库");
	
	private ApplyCode(String code, String desc) {
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