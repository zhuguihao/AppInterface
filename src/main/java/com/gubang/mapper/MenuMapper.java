package com.gubang.mapper;

import java.util.List;
import com.gubang.entity.Menu;
import com.gubang.vo.MenuVo;

public interface MenuMapper {
	List<Menu> selectMenuByGroup(MenuVo params);
	List<Menu> getMenu(Menu params);
	void updateByPrimaryKeySelective(Menu params);
	void insertSelective(Menu params);
	
}