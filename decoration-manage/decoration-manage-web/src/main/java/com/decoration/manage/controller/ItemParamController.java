package com.decoration.manage.controller;

import java.util.Date;
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
import com.decoration.manage.pojo.ItemParam;
import com.decoration.manage.service.ItemParamService;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("item/param")
public class ItemParamController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 根据商品类目id查询结果
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="{itemCatId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemParam> queryItemParamByItemId(@PathVariable("itemCatId") Long itemId){
		
		try {
			ItemParam itemParam=new ItemParam();
			itemParam.setItemCatId(itemId);
			return ResponseEntity.ok(this.itemParamService.queryOne(itemParam));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
	
	/**
	 * 分页查询商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<EasyUIResult> getItemParamList(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="rows",defaultValue="30") Integer rows){
		try {
			PageInfo<ItemParam> pageInfo = this.itemParamService.queryItemParamList(page, rows);
			return ResponseEntity.ok(new EasyUIResult(pageInfo.getTotal(), pageInfo.getList()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("查询商品失败，page= "+page+",rows "+rows,e);
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 新增商品规格参数模板
	 * @param itemCatId
	 * @param itemParam
	 * @return
	 */
	@RequestMapping(value="{itemCatId}",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId") Long itemCatId,ItemParam itemParam){
		try {
			itemParam.setCreated(new Date());
			itemParam.setUpdated(new Date());
			itemParam.setItemCatId(itemCatId);
			this.itemParamService.save(itemParam);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResponseEntity<Void> deleteItemParam(@RequestParam("ids") List<Object> ids){
		
		try {
			this.itemParamService.deleteByIds(ItemParam.class, ids);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
