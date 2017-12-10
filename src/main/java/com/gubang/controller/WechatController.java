package com.gubang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.gubang.dto.query.GetWechatOpenIdQuery;
import com.gubang.dto.result.GetWechatOpenIdResult;
import com.gubang.service.WechatService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;


/**
 * 和微信相关的服务
 * @author Administrator
 *
 */
@RestController
public class WechatController {

	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@Autowired
	private WechatService wechatService;
	
	/**
	 * 通过code来调用微信接口来获取用户openID
	 */
	@RequestMapping(value="getOpenIdByCode",method = RequestMethod.POST)
	public ResultDTO test(HttpServletRequest request, HttpServletResponse response, @RequestBody GetWechatOpenIdQuery getWechatOpenIdQuery) {
		log.info("test comes request");
		ResultDTO resultDTO = new ResultDTO();
		if (getWechatOpenIdQuery.inValid()) {
			return resultDTO.setFailObject();
		}
		String openid = wechatService.getOpenIdByCode(getWechatOpenIdQuery.getJsCode());
		GetWechatOpenIdResult getWechatOpenIdResult = new GetWechatOpenIdResult();
		if (!CommonUtil.isEmpty(openid)) {
			getWechatOpenIdResult.setOpenId(openid); 
			return resultDTO.setSuccess(getWechatOpenIdResult);
		} else {
			return resultDTO.setFailObject();
		}
	}
}