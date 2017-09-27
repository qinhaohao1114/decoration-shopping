package com.decoration.manage.controller;

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

import com.decoration.commom.bean.EasyUIResult;
import com.decoration.manage.pojo.Content;
import com.decoration.manage.service.ContentService;
import com.github.pagehelper.PageInfo;

@RequestMapping("content")
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<EasyUIResult> queryContentList(
			@RequestParam("categoryId") Long categoryId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows) {

		try {
			PageInfo<Content> pageInfo = this.contentService.queryPageList(categoryId, page, rows);
			
			return ResponseEntity.ok(new EasyUIResult(pageInfo.getTotal(), pageInfo.getList()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveContent(Content content){
		try {
			content.setId(null);
			this.contentService.save(content);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
