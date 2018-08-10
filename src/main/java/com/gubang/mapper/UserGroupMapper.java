package com.gubang.mapper;

import java.util.List;
import com.common.dto.RelationMenuDto;
import com.gubang.entity.MenuCenter;

public interface UserGroupMapper {
	int insertRoleMenu(RelationMenuDto params);
	List<String> getGroupMenuIds(MenuCenter params);
	int deleteRoleMenu(RelationMenuDto params);
}