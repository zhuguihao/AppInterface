package com.gubang.constant;

/**
 * 返回码定义
 * 
 * @author pc
 * 
 */
public enum ResultCode {

	SUCCESS("success", "成功"),
	FAIL("fail","失败"),
	NOT_FOUND("not_found","结果为空"),
	NOT_FOUND_PRODUCT("not_found_product","当前的产品不存在"),
	BARCODE_ERROR("barcode_error","当前产品码已经存在"),
	PARAMETER_ERROR("invalid_parameter", "参数无效"),
	SYSTEM_ERROR("system_error", "系统异常"),
	NOT_LOGIN("not_login", "用户没有登录");
	
	private ResultCode(String code, String desc) {
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