package com.gubang.constant;

/**
 * 产品替换或者维修
 * 
 * @author pc
 * 
 */
public enum ApplyPolicyStateCode {

	POLICY_STATUS("policy_status", "维修状态"),
	POLICY_REPLACE_STATUS("policy_replace_status","替换状态");
	
	private ApplyPolicyStateCode(String code, String desc) {
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