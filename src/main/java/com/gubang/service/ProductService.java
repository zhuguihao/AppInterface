package com.gubang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gubang.dao.ProductDetailInfoMapper;
import com.gubang.entity.ProductDetailInfo;

@Service
public class ProductService {

	@Autowired
	ProductDetailInfoMapper productDetailInfoMapper;
	
	public ProductDetailInfo selectByProductBarcode(String barcode) {
		return productDetailInfoMapper.selectByProductBarcode(barcode);
	}
}
