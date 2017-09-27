package com.decoration.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.decoration.manage.pojo.BasePojo;
import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseService<T extends BasePojo> {
	
	@Autowired
	private Mapper<T> mapper;

	private Mapper<T> getMapper() {

		return this.mapper;
	}

	public Integer deleteById(Long id) {

		return getMapper().deleteByPrimaryKey(id);
	}

	public Integer deleteByIds(Class<?> clazz, List<Object> values) {
		Example example = new Example(clazz);
		example.createCriteria().andIn("id", values);
		return this.getMapper().deleteByExample(example);
	}

	public Integer save(T t) {
		if (t.getCreated() == null) {
			t.setCreated(new Date());
			t.setUpdated(t.getCreated());
		} else if (t.getUpdated() == null) {
			t.setUpdated(t.getCreated());
		}
		return this.getMapper().insert(t);
	}

	public Integer saveSelective(T t) {
		if (t.getCreated() == null) {
			t.setCreated(new Date());
			t.setUpdated(t.getCreated());
		} else if (t.getUpdated() == null) {
			t.setUpdated(t.getCreated());
		}
		return this.getMapper().insertSelective(t);
	}

	public Integer updateById(T t) {
		t.setUpdated(new Date());
		return this.getMapper().updateByPrimaryKey(t);
	}

	public Integer updateSelectiveById(T t) {
		t.setUpdated(new Date());
		return this.getMapper().updateByPrimaryKeySelective(t);
	}

	public List<T> queryAll() {

		return getMapper().select(null);
	}

	public T queryById(Long id) {
		return getMapper().selectByPrimaryKey(id);
	}

	public Integer queryCount(T t) {
		return getMapper().selectCount(t);
	}

	public Integer queryCount() {
		return queryCount(null);
	}

	public List<T> queryListByWhere(T t) {

		return getMapper().select(t);
	}
	
	public T queryOne(T t){
		
		return getMapper().selectOne(t);
	}
	/**
	 * 分页查询
	 * 
	 * @param param
	 *            查询条件
	 * @param page
	 *            当前页
	 * @param rows
	 *            页面大小
	 * @return
	 */
	public PageInfo<T> queryPageListByWhere(T param, Integer page, Integer rows) {
		// 设置分页参数
		PageHelper.startPage(page, rows);
		List<T> list = this.queryListByWhere(param);
		return new PageInfo<T>(list);
	}

}
