package com.gubang.service.impl;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.ApplyCode;
import com.gubang.constant.Constant;
import com.gubang.dto.query.ProductSaleDelDto;
import com.gubang.dto.query.StorageDto;
import com.gubang.dto.query.OutStorageDto;
import com.gubang.entity.ProductSaleInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductInfoMapper;
import com.gubang.mapper.ProductSaleInfoMapper;
import com.gubang.service.ProductSaleService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductSaleVo;
import com.gubang.vo.ProductVo;

@Service
public class ProductSaleServiceImpl implements ProductSaleService {

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	ProductSaleInfoMapper productSaleInfoMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultDTO storage(StorageDto params, UserInfo userInfo) {
		// TODO Auto-generated method stub
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			ProductVo productVo = new ProductVo();
			productVo.setId(params.getProductId());
			if (null == productInfoMapper.getProduct(productVo)) {
				return result.setNotFoundProduct();
			}

			/**
			 * 流水号判重
			 */
			if (productSaleInfoMapper.selectProductSaleByBarcode(params.getBarCode()) > 0) {
				return result.setBarCodeError();
			}

			ProductSaleInfo record = new ProductSaleInfo();
			record.setId(CommonUtil.getUUid());
			record.setBarCode(params.getBarCode());
			record.setIsDel(Constant.DB_FALSE_FLAG);
			record.setCustomerId(userInfo.getId());
			record.setProductStatus(ApplyCode.APPLY_STORAGE.getCode());
			record.setProductId(params.getProductId());
			record.setCreateBy(userInfo.getId());
			record.setCreateDate(new Date());
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());

			if (productSaleInfoMapper.insert(record) < 1) {
				log.error(userInfo.getAccount() + ":产品售出表保存失败");
				return result.setSystemError();
			}

			/**
			 * 根据产品ID和产品出库状态查询产品信息
			 * 
			 */
			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setProductId(params.getProductId());
			psVo.setProductStatus(ApplyCode.APPLY_STORAGE.getCode());
			psVo.setBarCode(params.getBarCode());
			return result.setSuccess(productSaleInfoMapper.getProductSaleInfo(psVo));
		} catch (Exception e) {
			/**
			 * 回滚事务
			 */
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			log.error("入库失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO outStorage(OutStorageDto params, UserInfo userInfo) {
		// TODO Auto-generated method stub
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			/**
			 * 判断当前产品码的售出产品是否存在
			 */
			ProductSaleInfo productSaleInfo = new ProductSaleInfo();
			productSaleInfo.setBarCode(params.getBarCode());
			productSaleInfo.setProductStatus(ApplyCode.APPLY_STORAGE.getCode());
			productSaleInfo = productSaleInfoMapper.selectByProductSaleInfoParams(productSaleInfo);
			if (null == productSaleInfo) {
				return result.setNotFoundProduct();
			}

			ProductSaleInfo record = new ProductSaleInfo();
			record.setId(productSaleInfo.getId());
			record.setBarCode(params.getBarCode());
			record.setProductStatus(ApplyCode.APPLY_OUT_STORAGE.getCode());
			if (productSaleInfoMapper.updateByPrimaryKeySelective(record) < 1) {
				log.error(userInfo.getAccount() + ":修改售出表信息失败");
				return result.setSystemError();
			}
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("出库失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO delStorage(ProductSaleDelDto params, UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			ProductSaleInfo record = new ProductSaleInfo();
			record.setId(params.getId());
			record.setIsDel(Constant.DB_TRUE_FLAG);
			if (productSaleInfoMapper.updateByPrimaryKeySelective(record) < 1) {
				return result.setFail(new JSONObject());
			}

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("删除入库信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getStorageByUser(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setCreateBy(userInfo.getId());
			Date date = new Date();
			psVo.setCreateDateStart(date);
			psVo.setCreateDateEnd(CommonUtil.getAnotherDate(date, 1));
			psVo.setProductStatus(ApplyCode.APPLY_STORAGE.getCode());
			return result.setSuccess(productSaleInfoMapper.productSaleInfo(psVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("查询操作者当日入库信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}
}
