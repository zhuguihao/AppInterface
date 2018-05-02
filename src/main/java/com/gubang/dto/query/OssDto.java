package com.gubang.dto.query;

import java.io.Serializable;
import java.util.Date;

public class OssDto implements Serializable {

	private static final long serialVersionUID = -5937139615537847218L;
	private byte[] file;
	private String bucketName;
	private String fileName;
	private String fileId;
	private Date expiration;

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

}