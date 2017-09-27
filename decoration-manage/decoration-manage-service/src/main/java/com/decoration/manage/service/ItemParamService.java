package com.decoration.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decoration.manage.mapper.ItemParamMapper;
import com.decoration.manage.pojo.ItemParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemParamService extends BaseService<ItemParam> {

	@Autowired
	private ItemParamMapper itemParamMapper;

	public PageInfo<ItemParam> queryItemParamList(Integer page, Integer rows) {
		
		PageHelper.startPage(page, rows);
		
		List<ItemParam> itemParams = this.itemParamMapper.getAllItemParamList();

		return new PageInfo<ItemParam>(itemParams);
	}

}
