package com.decoration.manage.mapper;

import java.util.List;

import com.decoration.manage.pojo.Content;
import com.github.abel533.mapper.Mapper;

public interface ContentMapper extends Mapper<Content>{

	/**
	 * 根据分类id查询内容列表，按照时间倒叙排列
	 * @param categoryId
	 * @return
	 */
	public List<Content> queryListByCategoryId(Long categoryId);
}
