package com.gubang.vo;

import java.util.List;
import com.gubang.entity.UserInfo;

public class GroupUserVo {

	private List<UserInfo> userList;
	private List<String> checkedList;

	public List<UserInfo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfo> userList) {
		this.userList = userList;
	}

	public List<String> getCheckedList() {
		return checkedList;
	}

	public void setCheckedList(List<String> checkedList) {
		this.checkedList = checkedList;
	}

}
