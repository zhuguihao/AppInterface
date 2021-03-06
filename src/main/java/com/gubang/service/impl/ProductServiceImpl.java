package com.gubang.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.Constant;
import com.gubang.dto.query.ProductDto;
import com.gubang.entity.ProductInfo;
import com.gubang.entity.ProductNameInfo;
import com.gubang.entity.ProductSaleInfo;
import com.gubang.entity.ProductSeriesInfo;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.ProductInfoMapper;
import com.gubang.mapper.ProductNameInfoMapper;
import com.gubang.mapper.ProductSeriesInfoMapper;
import com.gubang.service.ProductService;
import com.gubang.service.RedisService;
import com.gubang.util.ResultDTO;
import com.gubang.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService {

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Autowired
	ProductInfoMapper productInfoMapper;

	@Autowired
	ProductNameInfoMapper productNameInfoMapper;

	@Autowired
	ProductSeriesInfoMapper productSeriesInfoMapper;

	@Resource
	private RedisService redisService;

	@Value(value = "${redis.product.cascade.timeout}")
	private Long timeout;

	@Override
	public ResultDTO getAllProductCascade() {
		// find from redis
		// if null, get from db,save to redis
		// else return
		ResultDTO result = redisService.get(Constant.REDIS_PRODUCT_ALL_KEY, Constant.REDIS_PRODUCT_ALL_KEY,
				ResultDTO.class);
		if (result == null) {
			result = new ResultDTO();
			result.setSuccess(doGetAllProductJson());
			synchronized (this.getClass()) {
				redisService.set(Constant.REDIS_PRODUCT_ALL_KEY, Constant.REDIS_PRODUCT_ALL_KEY, result, timeout);
			}
		}
		return result;
	}

	private JSONArray doGetAllProductJson() {
		List<ProductSeriesInfo> level1List = productSeriesInfoMapper.findAll();
		List<ProductNameInfo> level2List = productNameInfoMapper.findAll();
		List<ProductInfo> level3List = productInfoMapper.findAll();

		// 构建parentid和元素的map
		Map<String, List<ProductNameInfo>> level2Map = new LinkedHashMap<>();
		for (ProductNameInfo ele : level2List) {
			List<ProductNameInfo> list;
			if ((list = level2Map.get(ele.gettSeriesId())) == null) {
				list = new LinkedList<>();
				level2Map.put(ele.gettSeriesId(), list);
			}
			list.add(ele);
		}

		// 构建parentid和元素的map
		Map<String, List<ProductInfo>> level3Map = new LinkedHashMap<>();
		for (ProductInfo ele : level3List) {
			List<ProductInfo> list;
			if ((list = level3Map.get(ele.getProNameId())) == null) {
				list = new LinkedList<>();
				level3Map.put(ele.getProNameId(), list);
			}
			list.add(ele);
		}

		JSONArray retArray = new JSONArray();
		for (ProductSeriesInfo level1Ele : level1List) {
			JSONObject level1Json = new JSONObject();
			level1Json.put("id", level1Ele.getId());
			level1Json.put("name", level1Ele.getSeries());
			level1Json.put("code", level1Ele.getSeriesCode());
			JSONArray level2Array = new JSONArray();
			List<ProductNameInfo> productNameList = level2Map.get(level1Ele.getId());
			if (productNameList != null) {
				for (ProductNameInfo level2Ele : productNameList) {
					JSONObject level2Json = new JSONObject();
					level2Json.put("id", level2Ele.getId());
					level2Json.put("name", level2Ele.getProName());
					level2Json.put("code", level2Ele.getProCode());
					JSONArray level3Array = new JSONArray();
					List<ProductInfo> productList = level3Map.get(level2Ele.getId());
					if (productList != null) {
						for (ProductInfo level3Ele : productList) {
							JSONObject level3Json = new JSONObject();
							level3Json.put("id", level3Ele.getId());
							level3Json.put("name", level3Ele.getProModel());
							level3Array.add(level3Json);
						}
					}
					level2Json.put("descendant", level3Array);
					level2Array.add(level2Json);
				}
			}
			level1Json.put("descendant", level2Array);
			retArray.add(level1Json);
		}
		return retArray;
	}

	@Override
	public List<ProductSaleInfo> querySaleByPage(Integer startPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countSaleByPage(Integer startPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductSaleInfo getSaleById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSale(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void instore(String barcode, Integer productId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void outstore(Integer customerId, String barcode) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultDTO getProductById(ProductDto params, UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			ProductVo productVo = new ProductVo();
			productVo.setId(params.getId());
			return result.setSuccess(productInfoMapper.getProduct(productVo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("查询产品信息失败：" + e.getMessage());
			return result.setSystemError();
		}
	}
}
