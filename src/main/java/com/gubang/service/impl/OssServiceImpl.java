package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.gubang.dto.query.OssDto;
import com.gubang.service.OssService;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
	public URL downLoadUrl(OssDto params) {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		URL downLoadUrl = ossClient.generatePresignedUrl(params.getBucketName(), params.getFileName() , params.getExpiration());
		return downLoadUrl;
	}

	@Override
	public void downLoadStream(HttpServletResponse response,OssDto params) throws IOException {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSSObject ossObject = ossClient.getObject(params.getBucketName(), params.getFileName());
		// 读Object内容
		System.out.println("Object content:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
		ServletOutputStream output = response.getOutputStream();
		String line = null;
		while (true) {
			line = reader.readLine();
		    if (line == null) break;
		    System.out.println(line);
			output.write(line.getBytes());
			output.write(line.getBytes("UTF-8"));
		}
		
		//数据读取完成后，获取的流一定要显示close，否则会造成资源泄露
		reader.close();
		// 关闭client
		ossClient.shutdown();
	}
}
