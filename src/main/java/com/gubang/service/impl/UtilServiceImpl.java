package com.gubang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gubang.dto.query.DownloadDto;
import com.gubang.dto.query.OssDto;
import com.gubang.dto.query.UploadDto;
import com.gubang.entity.TFile;
import com.gubang.entity.UserInfo;
import com.gubang.mapper.TFileMapper;
import com.gubang.service.OssService;
import com.gubang.service.UtilService;
import com.gubang.util.CommonUtil;
import com.gubang.util.ResultDTO;
import net.sf.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
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
	public ResultDTO upload(UserInfo userInfo, UploadDto params) {
		ResultDTO result = new ResultDTO();
		try {
			if (null == userInfo) {
				return result.setNotLogin();
			}
			if (params.inValid()) {
				return result.setParameterInvalid();
			}
			/**
			 * 获取文件中的原名称
			 */
			String fileName = params.getFile().getOriginalFilename();
			/**
			 * 获取文件中的类型
			 */
			String type = "";
			if(fileName.indexOf(".")>-1){
				type = fileName.substring(fileName.lastIndexOf("."), fileName.length())=="."?"":fileName.substring(fileName.lastIndexOf("."), fileName.length());
			}
			fileName = CommonUtil.getFormatDate(new Date(), "yyyyMM/")+CommonUtil.getUUid()+type;
			/**
			 * 入库保存当前文件信息
			 */
			TFile file = new TFile();
			file.setFileId(CommonUtil.getUUid());
			file.setFileName(fileName);
			file.setFileOrginalName(params.getFile().getOriginalFilename());
			file.setFileSize(params.getFile().getSize());
			file.setFileType(type);
			file.setCreateBy(userInfo.getId());
			file.setCreateDate(new Date());
			file.setUpdateBy(userInfo.getId());
			file.setUpdateDate(new Date());
			
			tFileMapper.insert(file);
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
			
			return result.setSuccess(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(userInfo==null?"":userInfo.getAccount() + "上传OSS服务器失败：" + e.getMessage());
			return result.setSystemError();
		}
	}

	@Override
	public Object download(DownloadDto params) {
		try {
			if (params.inValid()) {
				return null;
			}
			TFile file = new TFile();
			file.setFileId(params.getFileId());
			
			TFile filelist = tFileMapper.selectByFileParams(file);
			
			if(null == filelist){
				return null;
			}
			
			OssDto oss = new OssDto();
			oss.setBucketName("gbbucket");
			oss.setFileName(filelist.getFileName());
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
			return null;
		}
	}
	
}
