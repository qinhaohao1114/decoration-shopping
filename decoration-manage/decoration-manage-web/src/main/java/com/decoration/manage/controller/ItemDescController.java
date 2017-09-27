package com.decoration.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.decoration.manage.pojo.ItemDesc;
import com.decoration.manage.service.ItemDescService;

@Controller
@RequestMapping("item/desc")
public class ItemDescController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private ItemDescService itemDescService;
	
	/**
	 * 根据id查询商品描述
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemDesc> queryItemDesc(@PathVariable("itemId") Long itemId){
		try {
			ItemDesc itemDesc = this.itemDescService.queryById(itemId);
			if(null==itemDesc){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.ok(itemDesc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
}
