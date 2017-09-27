package com.decoration.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.decoration.manage.pojo.ContentCategory;
import com.decoration.manage.service.ContentCategoryService;

@RequestMapping("content/category")
@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService categoryService;

	/**
	 * 根据parentId查询内容分类列表
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ContentCategory>> queryContentCategoryList(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		try {
			ContentCategory param = new ContentCategory();
			param.setParentId(parentId);
			List<ContentCategory> contentCategorys = this.categoryService
					.queryListByWhere(param);

			return ResponseEntity.ok(contentCategorys);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);

	}

	/**
	 * 新增分类
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ContentCategory> saveContentCategory(
			@RequestParam("parentId") Long parentId,
			@RequestParam("name") String name) {

		try {
			ContentCategory cateContentCategory = new ContentCategory();
			cateContentCategory.setParentId(parentId);
			cateContentCategory.setName(name);
			cateContentCategory.setCreated(new Date());
			cateContentCategory.setUpdated(cateContentCategory.getCreated());
			cateContentCategory.setId(null);
			cateContentCategory.setIsParent(false);
			cateContentCategory.setSortOrder(1);
			cateContentCategory.setStatus(1);

			this.categoryService.save(cateContentCategory);
			ContentCategory parent = this.categoryService.queryById(parentId);
			if (!parent.getIsParent()) {
				parent.setIsParent(true);
				parent.setUpdated(new Date());
				this.categoryService.updateById(parent);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(
					cateContentCategory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

	/**
	 * 重命名
	 * 
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ResponseEntity<Void> updateContentCategory(
			ContentCategory contentCategory) {
		try {
			contentCategory.setUpdated(new Date());
			this.categoryService.updateSelectiveById(contentCategory);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	/**
	 * 删除该节点，并且删除该节点下的子节点
	 * 
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public ResponseEntity<Void> deleteContentCategory(
			ContentCategory contentCategory) {
		try {
			List<Object> deletdIds=new ArrayList<Object>();
			deletdIds.add(contentCategory.getId());
			findAllSubNode(deletdIds,contentCategory.getId());
			this.categoryService.deleteByIds(ContentCategory.class, deletdIds);
			//判断父节点是否还有其他子节点
			ContentCategory param=new  ContentCategory();
			param.setParentId(contentCategory.getParentId());
			List<ContentCategory> contentCategories = this.categoryService.queryListByWhere(param);
			if(contentCategories.isEmpty()){
				ContentCategory parent=new ContentCategory();
				parent.setId(contentCategory.getParentId());
				parent.setIsParent(false);
				parent.setUpdated(new Date());
				this.categoryService.updateSelectiveById(parent);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	private void findAllSubNode(List<Object> deleteIds,Long parentId){
		ContentCategory param = new ContentCategory();
		param.setParentId(parentId);
		List<ContentCategory> contentCategories= this.categoryService.queryListByWhere(param);
		
		for(ContentCategory category:contentCategories){
			deleteIds.add(category.getId());
			if(category.getIsParent()){
				findAllSubNode(deleteIds,category.getId());
			}
		}
	}
}
