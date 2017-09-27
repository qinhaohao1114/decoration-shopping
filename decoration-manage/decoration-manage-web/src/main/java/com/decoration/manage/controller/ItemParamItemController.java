package com.decoration.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.decoration.manage.pojo.ItemParamItem;
import com.decoration.manage.service.ItemParamItemService;


@Controller
@RequestMapping("item/param/item")
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping(value="{itemId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemParamItem> getItemParamItemByItemId(@PathVariable("itemId") Long itemId){
		
		try {
			ItemParamItem itemParamItem=new ItemParamItem();
			itemParamItem.setItemId(itemId);
			return ResponseEntity.ok(this.itemParamItemService.queryOne(itemParamItem));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
}
