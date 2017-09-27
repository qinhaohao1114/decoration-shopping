package com.decoration.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.decoration.commom.bean.EasyUIResult;
import com.decoration.manage.pojo.Item;
import com.decoration.manage.service.ItemService;
import com.github.pagehelper.PageInfo;

@RequestMapping("item")
@Controller
public class ItemController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ItemController.class);
	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item,
			@RequestParam("desc") String desc,
			@RequestParam("itemParams") String itemParams) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("执行新增商品操作，item= {},itemDesc = {}", item, desc);
			}
			this.itemService.save(item, desc, itemParams);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("执行新增商品操作成功!id={}", item.getId());
			}
			//
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("新增商品失败!item=" + item, e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	/**
	 * 查询商品列表
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<EasyUIResult> queryItemList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows) {

		try {
			PageInfo<Item> pageInfo = this.itemService
					.queryItemList(page, rows);
			return ResponseEntity.ok(new EasyUIResult(pageInfo.getTotal(),
					pageInfo.getList()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("查询商品失败，page= " + page + ",rows " + rows, e);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				null);
	}

	/**
	 * 更新商品
	 * 
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ResponseEntity<Void> updateItem(Item item,
			@RequestParam("desc") String desc,
			@RequestParam("itemParams") String itemParams,
			@RequestParam("itemParamId") Long itemParamId) {

		try {
			this.itemService.upateItem(item, desc, itemParams,itemParamId);
			// 204
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public ResponseEntity<Void> deleteItem(@RequestParam("ids") List<Object> ids) {

		try {
			this.itemService.deleteByListIds(ids);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@RequestMapping(value="{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Item> getItemById(@PathVariable("itemId") Long itemId){
		
		try {
			Item item = this.itemService.queryById(itemId);
			if(null==item){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
}
