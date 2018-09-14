package com.gubang.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 售后申请单状态
 * 
 * @author pc
 * 
 */
public enum SaleApplyCode {

	FIRST_TRIAL("first_trial", "售后初审"),
	THE_TRIAL_PASS("the_trial_pass", "初审通过"),
	COURIER_TRACKING_REJECT("courier_tracking_reject", "快递驳回"),
	THE_TRIAL_REJECT("the_trial_reject","初审拒绝"),	
	COURIER_TRACKING("courier_tracking","客户快递"),
	AFTERSALE_DEPARTMENT("aftersale_department","售后部"),
	COURIER_DEPARTMENT("courier_department","售后寄件"),
	COMPANY_COURIER_TRACKING("company_courier_tracking","公司快递"),
	FINSH_APPLY("finsh_apply","售后完成");
	
	
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
	
	public static JSONArray getList(){
		JSONArray list = new JSONArray();
		for (SaleApplyCode item:SaleApplyCode.values()) {
			JSONObject obj = new JSONObject();
			obj.put("code",item.code);
			obj.put("desc",item.desc);
			list.add(obj);
		}
		System.out.println(JSON.toJSONString(list));
		return list;
	}
	
	public static void main(String[] args) {
		SaleApplyCode.getList();
	}
}