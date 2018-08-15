package com.gubang.mapper;

import java.util.List;
import com.common.dto.RelationGroupUserDto;
import com.gubang.entity.GroupUserCenter;

public interface UserGroupMapper {
	int insertRoleUser(RelationGroupUserDto params);
	List<String> getGroupUserIds(GroupUserCenter params);
	int deleteRoleUser(RelationGroupUserDto params);
}