package com.gubang.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.ResultCode;

public class ResultDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String msg;
	private Object data;

	public ResultDTO setSuccess(Object retData) {
		status = ResultCode.SUCCESS.getCode();
		msg = ResultCode.SUCCESS.getDesc();
		data = retData;
		return this;
	}

	public ResultDTO setFail(Object data) {
		status = ResultCode.FAIL.getCode();
		msg = ResultCode.FAIL.getDesc();
		this.data = data;
		return this;
	}

	public ResultDTO setFailObject() {
		status = ResultCode.FAIL.getCode();
		msg = ResultCode.FAIL.getDesc();
		this.data = new JSONObject();
		return this;
	}
	
	public ResultDTO setFailArray() {
		status = ResultCode.FAIL.getCode();
		msg = ResultCode.FAIL.getDesc();
		this.data = new JSONArray();
		return this;
	}
	
	public ResultDTO setNotFound() {
		status = ResultCode.NOT_FOUND.getCode();
		msg = ResultCode.NOT_FOUND.getDesc();
		return this;
	}
	
	/**
	 * 根据产品ID查询不到产品信息
	 * @return
	 */
	public ResultDTO setNotFoundProduct() {
		status = ResultCode.NOT_FOUND_PRODUCT.getCode();
		msg = ResultCode.NOT_FOUND_PRODUCT.getDesc();
		return this;
	}
	
	/**
	 * 根据产品ID查询不到售后单信息
	 * @return
	 */
	public ResultDTO setNotFoundApplyProduct() {
		status = ResultCode.NOT_FOUND_APPLY_PRODUCT.getCode();
		msg = ResultCode.NOT_FOUND_APPLY_PRODUCT.getDesc();
		return this;
	}
	
	/**
	 * 根据售后单ID查询不到客服信息
	 * @return
	 */
	public ResultDTO setNotFoundApplySys() {
		status = ResultCode.NOT_FOUND_APPLY_SYS.getCode();
		msg = ResultCode.NOT_FOUND_APPLY_SYS.getDesc();
		return this;
	}
	
	/**
	 * 未找到出售的产品信息
	 * @return
	 */
	public ResultDTO setNotSaleProduct() {
		status = ResultCode.NOT_SALE_PRODUCT.getCode();
		msg = ResultCode.NOT_SALE_PRODUCT.getDesc();
		return this;
	}
	
	/**
	 * 根据产品编号和产品状态查询不到产品信息
	 * @return
	 */
	public ResultDTO setNotFoundBarcodeError() {
		status = ResultCode.NOT_BARCODE_ERROR.getCode();
		msg = ResultCode.NOT_BARCODE_ERROR.getDesc();
		return this;
	}
	
	public ResultDTO setParameterInvalid() {
		status = ResultCode.PARAMETER_ERROR.getCode();
		msg = ResultCode.PARAMETER_ERROR.getDesc();
		return this;
	}
	
	public ResultDTO setParameterInvalid(String message) {
		status = ResultCode.PARAMETER_ERROR.getCode();
		msg = message;
		return this;
	}


	public ResultDTO setSystemError() {
		status = ResultCode.SYSTEM_ERROR.getCode();
		msg = ResultCode.SYSTEM_ERROR.getDesc();
		return this;
	}

	public ResultDTO setNotLogin() {
		status = ResultCode.NOT_LOGIN.getCode();
		msg = ResultCode.NOT_LOGIN.getDesc();
		return this;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 产品售出表流水号存在多个相同
	 * @return
	 */
	public ResultDTO setBarCodeError() {
		status = ResultCode.BARCODE_ERROR.getCode();
		msg = ResultCode.BARCODE_ERROR.getDesc();
		return this;
	}
	
	/**
	 * 账号不存在
	 * @return
	 */
	public ResultDTO setAccountError() {
		status = ResultCode.ACCOUNT_ERROR.getCode();
		msg = ResultCode.ACCOUNT_ERROR.getDesc();
		return this;
	}
	
	/**
	 * 密码错误
	 * @return
	 */
	public ResultDTO setPwdError() {
		status = ResultCode.PWD_ERROR.getCode();
		msg = ResultCode.PWD_ERROR.getDesc();
		return this;
	}
	
	/**
	 * 产品超过售后维修期
	 * @return
	 */
	public ResultDTO setPolicyError() {
		status = ResultCode.POLICY_ERROR.getCode();
		msg = ResultCode.POLICY_ERROR.getDesc();
		return this;
	}
	
	/**
	 * 产品超过替换期
	 * @return
	 */
	public ResultDTO setPolicyReplaceError() {
		status = ResultCode.POLICY_REPLACE_ERROR.getCode();
		msg = ResultCode.POLICY_REPLACE_ERROR.getDesc();
		return this;
	}

	@Override
	public String toString() {
		return "ResultDTO [status=" + status + ", msg=" + msg + ", data=" + (data == null ? "" : data.toString()) + "]";
	}
}
