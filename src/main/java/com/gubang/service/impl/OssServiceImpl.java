package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.aliyun.oss.OSSClient;
import com.gubang.dto.query.OssDto;
import com.gubang.service.OssService;
import java.io.ByteArrayInputStream;
import java.net.URL;

@Service
public class OssServiceImpl implements OssService {
	@Value("${oss.endpoint}")
	private String endpoint;
	@Value("${oss.accessKeyId}")
	private String accessKeyId;
	@Value("${oss.accessKeySecret}")
	private String accessKeySecret;

	@Override
	public void ossUpload(OssDto params) {

		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		ossClient.putObject(params.getBucketName(), params.getFileName(), new ByteArrayInputStream(params.getFile()));
		// 关闭client
		ossClient.shutdown();
	}

	@Override
	public Object downLoadUrl(OssDto params) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		URL downLoadUrl = ossClient.generatePresignedUrl(params.getBucketName(), params.getFileName() , params.getExpiration());
		return downLoadUrl;
	}
}
