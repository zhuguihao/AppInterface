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
	NOT_FOUND_APPLY_PRODUCT("not_found_apply_product","当前的售后产品不存在"),
	NOT_FOUND_APPLY_SYS("not_found_apply_sys","当前的客服售后信息不存在"),
	BARCODE_ERROR("barcode_error","当前产品编号已存在"),
	NOT_BARCODE_ERROR("not_barcode_error","当前产品编号不存在"),
	PARAMETER_ERROR("invalid_parameter", "参数无效"),
	SYSTEM_ERROR("system_error", "系统异常"),
	POLICY_ERROR("policy_error", "该产品已经超过保修期，请联系商家"),
	POLICY_REPLACE_ERROR("policy_replace_error", "该产品已经超过替换期，请联系商家"),
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