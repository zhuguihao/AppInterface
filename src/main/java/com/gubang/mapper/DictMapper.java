package com.gubang.mapper;

import java.util.List;
import com.gubang.entity.Dict;

public interface DictMapper {
	List<Dict> getDict(Dict params);

	int editDict(Dict params);
}