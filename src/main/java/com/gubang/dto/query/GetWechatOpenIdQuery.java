package com.gubang.dto.query;

import com.gubang.util.CommonUtil;

/**
 * 用code换取openID
 * @author liangwenhan
 */
public class GetWechatOpenIdQuery {

	private String jsCode;
	
	public GetWechatOpenIdQuery(){}
	public GetWechatOpenIdQuery(String jsCode) {
		super();
		this.jsCode = jsCode;
	}

	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	
	public boolean inValid() {
		return CommonUtil.isEmpty(jsCode);
	}
}
