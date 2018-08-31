package com.common.dto;

import com.gubang.util.CommonUtil;

public class SaleAfterDto {
	/**
	 * 售后单状态
	 * FIRST_TRIAL("first_trial", "售后初审"),
	 * THE_TRIAL_PASS("the_trial_pass", "初审通过"),
	 * COURIER_TRACKING_REJECT("courier_tracking_reject", "快递驳回"),
	 * THE_TRIAL_REJECT("the_trial_reject","初审拒绝"),
	 * COURIER_TRACKING("courier_tracking","客户快递"),
	 * AFTERSALE_DEPARTMENT("aftersale_department","售后部"),
	 * COURIER_DEPARTMENT("courier_department","售后寄件"),
	 * COMPANY_COURIER_TRACKING("company_courier_tracking","公司快递"),
	 * FINSH_APPLY("finsh_apply","售后完成");
	 */
	private String applyStatus;

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public boolean inValid() {
		return CommonUtil.isEmpty(applyStatus);
	}
}
