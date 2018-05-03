package com.gubang.dto.query;

import java.io.Serializable;

import com.alibaba.druid.util.StringUtils;

public class DownloadDto implements Serializable {
	private static final long serialVersionUID = -947018389819441284L;
	private String fileId;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public boolean inValid() {
		return StringUtils.isEmpty(fileId);
	}
}