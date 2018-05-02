package com.gubang.dto.query;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class UploadDto implements Serializable {
	private static final long serialVersionUID = -947018389819441284L;
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public boolean inValid() {
		return file==null;
	}
}