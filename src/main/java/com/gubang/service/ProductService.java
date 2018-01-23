package com.gubang.service;

import com.gubang.util.ResultDTO;

public interface ProductService {

	/**
	 * get 3level data of all
	 * @return
	 */
	ResultDTO getAllProductCascade();
	
}
