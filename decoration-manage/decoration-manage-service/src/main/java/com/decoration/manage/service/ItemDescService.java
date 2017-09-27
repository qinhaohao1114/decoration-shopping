package com.decoration.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decoration.manage.mapper.ItemDescMapper;
import com.decoration.manage.pojo.ItemDesc;
import com.github.abel533.entity.Example;

@Service
public class ItemDescService extends BaseService<ItemDesc>{

	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Transactional
	public Integer deleteByIds(List<Object> values){
        Example example = new Example(ItemDesc.class);
        example.createCriteria().andIn("itemId", values);
        return this.itemDescMapper.deleteByExample(example);
    }
}
