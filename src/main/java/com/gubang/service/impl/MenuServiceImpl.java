package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.gubang.constant.Constant;
import com.gubang.entity.Menu;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.MenuMapper;
import com.gubang.service.MenuService;
import com.gubang.util.ResultDTO;
import com.gubang.vo.MenuQueryVo;
import com.gubang.vo.MenuVo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Override
	public ResultDTO selectMenuByGroup(UserInfo userInfo) {
		ResultDTO result = new ResultDTO();
		try {
			MenuVo menuVo = new MenuVo();
			if(null == userInfo){
				menuVo.setGroupId(Constant.NO_USER_MENU_KEY);
			}else if(StringUtils.isEmpty(userInfo.getGroupId())){
				menuVo.setGroupId(Constant.NO_USER_MENU_KEY);
			}else{
				menuVo.setGroupId(userInfo.getGroupId());
			}
			
			List<Menu> menus = menuMapper.selectMenuByGroup(menuVo);
			JSONArray retArr = new JSONArray();
			for(Menu menu:menus){
				MenuQueryVo menuQueryVo = new MenuQueryVo();
				menuQueryVo.setMenuName(menu.getMenuName());
				menuQueryVo.setMenuUrl(menu.getMenuUrl());
				menuQueryVo.setMenuIcon(menu.getMenuIcon());
				menuQueryVo.setMenuParams(menu.getMenuParams());
				retArr.add(menuQueryVo);
			}
			
			return result.setSuccess(retArr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo.getAccount() + "查询客户菜单失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

}
