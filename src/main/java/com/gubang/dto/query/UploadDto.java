package com.gubang.dto.query;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;

public class UploadDto implements Serializable {
	private static final long serialVersionUID = -947018389819441284L;
	private MultipartFile file;
	private String applyId;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public boolean inValid() {
		return file == null && StringUtils.isEmpty(applyId);
	}
}