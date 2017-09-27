package com.decoration.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decoration.manage.mapper.ContentMapper;
import com.decoration.manage.pojo.Content;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContentService extends BaseService<Content> {

	@Autowired
	private ContentMapper contentMapper;

	public PageInfo<Content> queryPageList(Long categoryId, Integer page, Integer rows) {
		PageHelper.startPage(page, rows, true);

		return new PageInfo<Content>(this.contentMapper.queryListByCategoryId(categoryId));
	}

}
