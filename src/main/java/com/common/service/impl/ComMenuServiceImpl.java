package com.common.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.common.dto.GroupMenuDto;
import com.common.service.ComMenuService;
import com.gubang.entity.Menu;
import com.gubang.entity.MenuCenter;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.MenuCenterMapper;
import com.gubang.mapper.MenuMapper;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;

@Service
public class ComMenuServiceImpl implements ComMenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private MenuCenterMapper menuCenterMapper;

	@Override
	public ResultDTO getMenu(UserInfo userInfo, Menu params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			return result.setSuccess(menuMapper.getMenu(params));
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO editMenu(UserInfo userInfo, Menu params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			menuMapper.updateByPrimaryKeySelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO addMenu(UserInfo userInfo, Menu params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			params.setId(CommonUtil.getUUid());
			params.setParentId(
					StringUtils.isEmpty(params.getParentId()) ? userInfo.getGroupId() : params.getParentId());
			params.setCreateBy(userInfo.getId());
			params.setCreateDate(new Date());
			params.setUpdateBy(userInfo.getId());
			params.setUpdateDate(new Date());
			menuMapper.insertSelective(params);
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getGroupMenu(UserInfo userInfo, GroupMenuDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}

			if (params.inValid()) {
				return result.setParameterInvalid();
			}

			Menu menu = new Menu();
			menu.setType(params.getType());
			List<Menu> getMenu = menuMapper.getMenu(menu);
			MenuCenter menuCenter = new MenuCenter();
			menuCenter.settSysGroupId(params.getRoleId());
			JSONObject obj = new JSONObject();
			/**
			 * 已选择的菜单那列表
			 */
			obj.put("checked", menuCenterMapper.getGroupMenuIds(menuCenter));
			obj.put("menuList", CommonUtil.formatMenu(getMenu));
			return result.setSuccess(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

	@Override
	public ResultDTO getWebMenu(UserInfo userInfo, Menu params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			return result.setSuccess(CommonUtil.formatMenu(menuMapper.getMenu(params)));
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}

}