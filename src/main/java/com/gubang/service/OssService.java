package com.gubang.service;

import com.gubang.dto.query.OssDto;

public interface OssService {

	public void ossUpload(OssDto params);

	public Object downLoadUrl(OssDto params);
}
