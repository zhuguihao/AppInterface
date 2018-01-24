package com.gubang.service;

import com.gubang.util.ResultDTO;

public interface CustomerService {
	
	/**
	 * get customer and company data
	 * @return
	 */
	ResultDTO getCompanyCustomerCascade();
	
}
