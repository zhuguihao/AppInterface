package com.gubang.service;

import java.util.List;

import com.gubang.dto.query.ProductDto;
import com.gubang.dto.query.StorageDto;
import com.gubang.dto.query.OutStorageDto;
import com.gubang.entity.ProductSaleInfo;
import com.gubang.entity.UserInfo;
import com.gubang.util.ResultDTO;

public interface ProductService {

	/**
	 * get 3level data of all
	 * @return
	 */
	ResultDTO getAllProductCascade();
	
	List<ProductSaleInfo> querySaleByPage(Integer startPage, Integer pageSize);
	
	Integer countSaleByPage(Integer startPage, Integer pageSize);
	
	ProductSaleInfo getSaleById(Integer id);
	
	void deleteSale(Integer id);
	
	void instore(String barcode, Integer productId);
	
	void outstore(Integer customerId, String barcode);
	
	ResultDTO getProductById(ProductDto params,UserInfo userInfo);
	
	ResultDTO storage(StorageDto params,UserInfo userInfo);
	
	ResultDTO outStorage(OutStorageDto params,UserInfo userInfo);
}
