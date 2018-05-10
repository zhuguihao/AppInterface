package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.gubang.dto.query.OssDto;
import com.gubang.service.OssService;
import com.gubang.util.CommonUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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
		URL downLoadUrl = ossClient.generatePresignedUrl(params.getBucketName(), params.getFileName(),
				params.getExpiration());
		ossClient.shutdown();
		return downLoadUrl;
	}

	@Override
	public String downLoadStream(HttpServletResponse response, OssDto params) throws IOException {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSSObject ossObject = ossClient.getObject(params.getBucketName(), params.getFileName());
		// 读Object内容
		System.out.println("Object content:");
		InputStream in = ossObject.getObjectContent();
		 byte[] bytes = new byte[in.available()];
		 in.read(bytes, 0, in.available());
		 in.close();
	        byte[] data = bytes;
	        response.setContentType("image/png");
	        OutputStream out = response.getOutputStream();
	        out.write(data);
	        out.flush();
	        out.close();
		// 将文件中的内容读入到数组中
         in.read(bytes);
		// 关闭client
		ossClient.shutdown();
		return CommonUtil.Encoder(bytes);
	}
}
