package com.gubang.mapper;

import java.util.List;
import com.gubang.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo findByAccount(String account);

	List<UserInfo> getUser(UserInfo params);
}