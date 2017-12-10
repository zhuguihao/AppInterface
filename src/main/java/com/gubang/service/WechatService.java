package com.gubang.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

@Service
public class WechatService {

	private final static Logger log = LoggerFactory.getLogger("Admin");
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${wechat.getOpenIdByCode}")
	private String getOpenIdByCodeUrl;
	
	@Value("${wechat.appId}")
	private String wechatAppId;
	
	@Value("${wechat.secret}")
	private String wechatSecret;
	
	public String getOpenIdByCode(String code) {
		String requestUrl = String.format(getOpenIdByCodeUrl, wechatAppId, wechatSecret, code);
		log.info("begin to request wx to get openid : " + requestUrl);
		String wechatResult = restTemplate.getForObject(requestUrl, String.class);
		log.info("wx to get openid result: " + wechatResult);
		JSONObject json = JSONObject.parseObject(wechatResult);
		if (json.containsKey("openid")) {
			return json.getString("openid");
		} else {
			return null;
		}
	}
}
