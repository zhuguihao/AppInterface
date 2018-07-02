package com.gubang.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.ApplyCode;
import com.gubang.constant.Constant;
import com.gubang.dto.query.ProductSaleDelDto;
import com.gubang.dto.query.SoldDto;
import com.gubang.dto.query.StorageDto;
import com.gubang.dto.result.SoldProductResult;
import com.gubang.dto.query.OutStorageDto;
import com.gubang.entity.ProductSaleInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductInfoMapper;
import com.gubang.mapper.ProductSaleInfoMapper;
import com.gubang.service.ProductSaleService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import com.gubang.vo.GetProductSaleVo;
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
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());
			if (productSaleInfoMapper.updateByPrimaryKeySelective(record) < 1) {
				log.error(userInfo.getAccount() + ":修改售出表信息失败");
				return result.setSystemError();
			}

			/**
			 * 根据产品ID和产品出库状态查询产品信息
			 * 
			 */
			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setProductId(productSaleInfo.getProductId());
			psVo.setProductStatus(ApplyCode.APPLY_OUT_STORAGE.getCode());
			psVo.setBarCode(params.getBarCode());
			return result.setSuccess(productSaleInfoMapper.getProductSaleInfo(psVo));
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
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());
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

			/**
			 * 修改为查询当前任所有的数据
			 * 20180525
			 */
//			Date date = new Date();
//			psVo.setCreateDateStart(date);
//			psVo.setCreateDateEnd(CommonUtil.getAnotherDate(date, 1));
			psVo.setProductStatus(ApplyCode.APPLY_STORAGE.getCode());
			return result.setSuccess(productSaleInfoMapper.productSaleInfo(psVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("查询操作者当日入库信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getOutStorageByUser(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setCreateBy(userInfo.getId());
			/**
			 * 查询当前人所有的数据
			 * 20180525
			 */
//			Date date = new Date();
//			psVo.setCreateDateStart(date);
//			psVo.setCreateDateEnd(CommonUtil.getAnotherDate(date, 1));
			psVo.setProductStatus(ApplyCode.APPLY_OUT_STORAGE.getCode());
			return result.setSuccess(productSaleInfoMapper.productSaleInfo(psVo));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询操作者当日出库信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultDTO soldTime(UserInfo userInfo, SoldDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			/**
			 * 1.查询当前的产品编号是否存在于出库单中（产品编号和出库状态的产品）
			 */

			ProductSaleVo productSaleVo = new ProductSaleVo();
			productSaleVo.setBarCode(params.getBarCode());
			productSaleVo.setProductStatus(ApplyCode.APPLY_OUT_STORAGE.getCode());
			GetProductSaleVo getProductSaleVo = productSaleInfoMapper.getProductSaleInfo(productSaleVo);

			if (null == getProductSaleVo) {
				return result.setNotFoundBarcodeError();
			}

			/**
			 * 2.是否已经离生产的日期超过保修，超过保修则提示需要联系售后部门进行增长售后维修时间
			 */
			Date date = new Date();
			/**
			 * 商品售出时已经超过厂家提供的售后维修时间，需要联系厂家进行处理。
			 */
			if (null != getProductSaleVo.getPolicy()) {
				Date policyDate = CommonUtil.addDate(getProductSaleVo.getCreateDate(),
						getProductSaleVo.getPolicy() * 12, "MONTH");
				if (policyDate.getTime() < date.getTime()) {
					return result.setPolicyError();
				}
			}
			if (null != getProductSaleVo.getPolicyReplace()) {
				Date policyReplaceDate = CommonUtil.addDate(getProductSaleVo.getCreateDate(),
						getProductSaleVo.getPolicyReplace() * 12, "MONTH");
				if (policyReplaceDate.getTime() < date.getTime()) {
					return result.setPolicyReplaceError();
				}
			}

			/**
			 * 3.生成保修日期
			 */
			ProductSaleInfo record = new ProductSaleInfo();
			record.setId(getProductSaleVo.getId());
			record.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			record.setUpdateBy(userInfo.getId());
			record.setAfterSaleTime(new Date());
			record.setSalesMan(userInfo.getId());
			record.setUpdateDate(new Date());
			if (productSaleInfoMapper.updateByPrimaryKeySelective(record) < 1) {
				log.error(userInfo.getAccount() + ":生成维修时间失败");
				return result.setSystemError();
			}

			/**
			 * 4.根据产品ID和产品出库状态查询产品信息
			 * 
			 */
			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setProductId(getProductSaleVo.getProductId());
			psVo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			psVo.setBarCode(params.getBarCode());
			GetProductSaleVo getProductSale = productSaleInfoMapper.getProductSaleInfo(psVo);

			/**
			 * 组装返回报文
			 */
			SoldProductResult soldProductResult = new SoldProductResult();
			soldProductResult.setId(getProductSale.getId());
			soldProductResult.setBarCode(getProductSale.getBarCode());
			soldProductResult
					.setAfterSaleTime(CommonUtil.getFormatDate(getProductSale.getAfterSaleTime(), "yyyy-MM-dd"));
			soldProductResult.setProDesc(getProductSale.getProDesc());
			soldProductResult.setProModel(getProductSale.getProModel());
			soldProductResult.setProName(getProductSale.getProName());
			soldProductResult.setSeries(getProductSale.getSeries());
			soldProductResult.setVoltageRange(getProductSale.getVoltageRange());
			soldProductResult.setPolicy(getProductSale.getPolicy());
			soldProductResult.setPolicyReplace(getProductSale.getPolicyReplace());
			return result.setSuccess(soldProductResult);
		} catch (Exception e) {
			/**
			 * 回滚事务
			 */
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			log.error("售后人员生成售后维修时间失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getSoldByUser(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			ProductSaleVo psVo = new ProductSaleVo();
			psVo.setCreateBy(userInfo.getId());
			/**
			 * 查询当前人所有的信息
			 * 20180525
			 */
//			Date date = new Date();
//			psVo.setCreateDateStart(date);
//			psVo.setCreateDateEnd(CommonUtil.getAnotherDate(date, 1));
			psVo.setProductStatus(ApplyCode.APPLY_SOLD_PRODUCT.getCode());
			List<GetProductSaleVo> productSaleInfo = productSaleInfoMapper.productSaleInfo(psVo);
			JSONArray retArr = new JSONArray();
			for (GetProductSaleVo retobj : productSaleInfo) {
				SoldProductResult soldProductResult = new SoldProductResult();
				soldProductResult.setId(retobj.getId());
				soldProductResult.setBarCode(retobj.getBarCode());
				soldProductResult
						.setAfterSaleTime(CommonUtil.getFormatDate(retobj.getAfterSaleTime(), "yyyy-MM-dd"));
				soldProductResult.setProDesc(retobj.getProDesc());
				soldProductResult.setProModel(retobj.getProModel());
				soldProductResult.setProName(retobj.getProName());
				soldProductResult.setSeries(retobj.getSeries());
				soldProductResult.setVoltageRange(retobj.getVoltageRange());
				soldProductResult.setPolicy(retobj.getPolicy());
				soldProductResult.setPolicyReplace(retobj.getPolicyReplace());
				retArr.add(soldProductResult);
			}
			return result.setSuccess(retArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("查询操作者当日售出产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO delOutStorage(ProductSaleDelDto params, UserInfo userInfo) {
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
			record.setProductStatus(ApplyCode.APPLY_STORAGE.getCode());
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());
			if (productSaleInfoMapper.updateByPrimaryKeySelective(record) < 1) {
				return result.setFail(new JSONObject());
			}

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("删除出库信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO delSold(ProductSaleDelDto params, UserInfo userInfo) {
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
			record.setProductStatus(ApplyCode.APPLY_OUT_STORAGE.getCode());
			record.setUpdateBy(userInfo.getId());
			record.setUpdateDate(new Date());
			if (productSaleInfoMapper.updateByPrimaryKeySelective(record) < 1) {
				return result.setFail(new JSONObject());
			}

			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("删除售出产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}
}
