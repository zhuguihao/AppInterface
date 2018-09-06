package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;
import com.gubang.dto.query.DownloadDto;
import com.gubang.dto.query.OssDto;
import com.gubang.dto.query.UploadDto;
import com.gubang.entity.TFile;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.TFileMapper;
import com.gubang.service.OssService;
import com.gubang.service.UtilService;
import com.gubang.util.CommonUtil;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UtilServiceImpl implements UtilService {

	private final static Logger log = LoggerFactory.getLogger("Admin");

	@Autowired
	private OssService ossService;
	@Autowired
	private TFileMapper tFileMapper;

	@Override
	public void upload(UserInfo userInfo, UploadDto params) throws Exception {
		/**
		 * 获取文件中的原名称
		 */
		String fileName = params.getFile().getOriginalFilename();
		/**
		 * 获取文件中的类型
		 */
		String type = "";
		if (fileName.indexOf(".") > -1) {
			type = fileName.substring(fileName.lastIndexOf("."), fileName.length()) == "." ? ""
					: fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		fileName = CommonUtil.getFormatDate(new Date(), "yyyyMM/")+ CommonUtil.getFormatDate(new Date(), "dd/") + CommonUtil.getUUid() + type;
		/**
		 * 入库保存当前文件信息
		 */
		TFile file = new TFile();
		file.setFileId(CommonUtil.getUUid());
		file.setApplyId(params.getApplyId());
		file.setFileName(fileName);
		file.setFileOrginalName(params.getFile().getOriginalFilename());
		file.setFileSize(params.getFile().getSize());
		file.setFileType(type);
		/**
		 * 创建OSS文件
		 */
		OssDto oss = new OssDto();
		oss.setBucketName("gbbucket");
		oss.setFile(params.getFile().getBytes());
		oss.setFileName(fileName);
		/**
		 * 上传到OSS服务器
		 */
		ossService.ossUpload(oss);
		Date expiration = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(expiration);
		calendar.add(Calendar.MONTH, 10);
		expiration = calendar.getTime();
		oss.setExpiration(expiration);
		file.setDownLoadUrl(ossService.downLoadUrl(oss).toString());
		file.setCreateBy(null==userInfo?"":userInfo.getId());
		file.setCreateDate(new Date());
		file.setUpdateBy(null==userInfo?"":userInfo.getId());
		file.setUpdateDate(new Date());

		tFileMapper.insert(file);
		
	}

	@Override
	public URL downloadUrl(DownloadDto params) {
		try {
			if (params.inValid()) {
				return null;
			}
//			TFile file = new TFile();
//			file.setFileId(params.getFileId());

//			TFile filelist = tFileMapper.selectByFileParams(file);

//			if (null == filelist) {
//				return null;
//			}

			OssDto oss = new OssDto();
			oss.setBucketName("gbbucket");
//			oss.setFileName(filelist.getFileName());
			Date expiration = new Date(System.currentTimeMillis());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(expiration);
			calendar.add(Calendar.MONTH, 10);
			expiration = calendar.getTime();
			oss.setExpiration(expiration);

			return ossService.downLoadUrl(oss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("获取OSS下载地址");
			return null;
		}
	}

	@Override
	public String downLoadStream(HttpServletResponse response, DownloadDto params) {
		try {
			TFile file = new TFile();
			file.setFileId(params.getFileId());
//			TFile filelist = tFileMapper.selectByFileParams(file);

			OssDto oss = new OssDto();
			oss.setBucketName("gbbucket");
//			oss.setFileName(filelist.getFileName());
			oss.setFileName("201805/0504d42fed2d4436b85f981427e2948a.jpg");
			Date expiration = new Date(System.currentTimeMillis());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(expiration);
			calendar.add(Calendar.MONTH, 10);
			expiration = calendar.getTime();
			oss.setExpiration(expiration);
			return ossService.downLoadStream(response, oss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String uploadBase64(HttpServletResponse response, DownloadDto base64) {
		OssDto oss = new OssDto();
		oss.setBucketName("gbbucket");
		if(StringUtils.isEmpty(base64.getFileId())){
			return null;
		}
		oss.setFile(Base64.decodeBase64(base64.getFileId()));
		oss.setFileName("test.jpg");
		Date expiration = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(expiration);
		calendar.add(Calendar.MONTH, 10);
		expiration = calendar.getTime();
		oss.setExpiration(expiration);
		ossService.ossUpload(oss);
		System.out.println(ossService.downLoadUrl(oss));
		return null;
	}

}
