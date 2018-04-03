package com.gubang.constant;

/**
 * 售后申请单状态
 * 
 * @author pc
 * 
 */
public enum SaleApplyCode {

	FIRST_TRIAL("first_trial", "售后初审"),
	THE_TRIAL_PASS("the_trial_pass", "初审通过"),
	THE_TRIAL_REJECT("the_trial_reject","初审拒绝"),
	APPLY_WAY_BILL("apply_way_bill", "客户填写运单号"),
	COURIER_TRACKING("courier_tracking","客户快递"),
	COMPANY_COURIER_TRACKING("company_courier_tracking","公司快递"),
	AFTERSALE_DEPARTMENT("aftersale_department","售后部"),
	COURIER_DEPARTMENT("courier_department","售后寄件"),
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
}