package com.decoration.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decoration.manage.mapper.ItemParamItemMapper;
import com.decoration.manage.pojo.ItemParamItem;
import com.github.abel533.entity.Example;

@Service
public class ItemParamItemService extends BaseService<ItemParamItem>{

	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	
	public Integer deleteByIds(List<Object> values){
        Example example = new Example(ItemParamItem.class);
        example.createCriteria().andIn("itemId", values);
        return this.itemParamItemMapper.deleteByExample(example);
    }
}
