package com.decoration.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.decoration.commom.bean.ItemCatResult;
import com.decoration.manage.pojo.ItemCat;
import com.decoration.manage.service.ItemCatService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {
	
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ItemCatController.class);
	@Autowired
	private ItemCatService itemCatService;
	private static final ObjectMapper mapper = new ObjectMapper();
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ItemCat>> queryItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		
		try {
			ItemCat param = new ItemCat();
			param.setParentId(parentId);
			List<ItemCat> itemCats = this.itemCatService.queryListByWhere(param);
			return ResponseEntity.ok(itemCats);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("查询类别失败****", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 提供前台系统的查询所有类目的借口
	 * @return
	 */
	@RequestMapping(value="web/all",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> queryAllItemCat(@RequestParam("callback") String callback){
		try {
			ItemCatResult itemCatResult	= this.itemCatService.queryAllItemCat();
			String json=mapper.writeValueAsString(itemCatResult);
			return ResponseEntity.ok(callback+"("+json+")");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
}
