package com.gubang.mapper;

import java.util.List;
import com.gubang.entity.Group;

public interface GroupMapper {
	List<Group> getGroup(Group params);

	int editGroup(Group params);

	int addGroup(Group params);
	
}