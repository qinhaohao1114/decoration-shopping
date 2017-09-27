package com.decoration.manage.mapper;

import java.util.List;

import com.decoration.manage.pojo.ItemParam;
import com.github.abel533.mapper.Mapper;

public interface ItemParamMapper extends Mapper<ItemParam>{

	public List<ItemParam> getAllItemParamList();
}
