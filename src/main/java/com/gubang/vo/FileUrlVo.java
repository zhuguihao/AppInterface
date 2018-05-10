package com.gubang.vo;

import java.io.Serializable;

import com.gubang.entity.CommonEntity;

public class FileUrlVo extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
