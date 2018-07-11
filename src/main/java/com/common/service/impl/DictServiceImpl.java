package com.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.common.service.DictService;
import com.gubang.entity.Dict;
import com.gubang.mapper.DictMapper;
import com.gubang.util.ResultDTO;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public ResultDTO getDict() {
		ResultDTO result = new ResultDTO();
		try {
			Dict params = new Dict();
			List<Dict> getDict = dictMapper.getDict(params );
			return result.setSuccess(getDict);
		} catch (Exception e) {
			e.printStackTrace();
			return result.setSystemError();
		}
	}
}