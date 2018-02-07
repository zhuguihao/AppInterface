package com.gubang.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gubang.constant.Constant;
import com.gubang.entity.CusCompany;
import com.gubang.entity.Customer;
import com.gubang.mapper.CusCompanyMapper;
import com.gubang.mapper.CustomerMapper;
import com.gubang.service.CustomerService;
import com.gubang.service.RedisService;
import com.gubang.util.ResultDTO;

@Service
public class CustomerServiceImpl implements CustomerService {

//	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CusCompanyMapper cusCompanyMapper;

	@Resource
    private RedisService redisService;
	
	
	@Value(value="${redis.customer.cascade.timeout}")
	private Long timeout;
	
	@Override
	public ResultDTO getCompanyCustomerCascade() {
		//find from redis
		//if null, get from db,save to redis
		//else return
		ResultDTO result = redisService.get(Constant.REDIS_PRODUCT_ALL_KEY, 
						Constant.REDIS_PRODUCT_ALL_KEY, ResultDTO.class);
		if (result == null) {
			result = new ResultDTO();
			result.setSuccess(doGetAllCustomerJson());
			synchronized(this.getClass()) {
				redisService.set(Constant.REDIS_PRODUCT_ALL_KEY, Constant.REDIS_PRODUCT_ALL_KEY, 
						result, timeout);
			}
		}
		return result;
	}
	
	private JSONArray doGetAllCustomerJson() {
		List<CusCompany> level1List = cusCompanyMapper.findAll();
		List<Customer> level2List = customerMapper.findAll();
		
		//构建parentid和元素的map
		Map<String, List<Customer>> level2Map = new LinkedHashMap<>();
		for (Customer ele : level2List) {
			List<Customer> list;
			if ((list = level2Map.get(ele.getCompanyId())) == null) {
				list = new LinkedList<>();
				level2Map.put(ele.getCompanyId(), list);
			}
			list.add(ele);
		}

		JSONArray retArray = new JSONArray();
		for (CusCompany level1Ele : level1List) {
			JSONObject level1Json = new JSONObject();
			level1Json.put("id", level1Ele.getId());
			level1Json.put("name", level1Ele.getName());
			JSONArray level2Array = new JSONArray();
			List<Customer> customerList = level2Map.get(level1Ele.getId());
			if (customerList != null) {
				for (Customer level2Ele : customerList) {
					JSONObject level2Json = new JSONObject();
					level2Json.put("id", level2Ele.getId());
					level2Json.put("name", level2Ele.getName());
					level2Array.add(level2Json);
				}
			}
			level1Json.put("descendant", level2Array);
			retArray.add(level1Json);
		}
		return retArray;
	}
}
