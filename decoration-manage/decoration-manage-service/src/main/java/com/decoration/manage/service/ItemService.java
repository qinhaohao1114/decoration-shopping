package com.decoration.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.decoration.manage.mapper.ItemMapper;
import com.decoration.manage.pojo.Item;
import com.decoration.manage.pojo.ItemDesc;
import com.decoration.manage.pojo.ItemParam;
import com.decoration.manage.pojo.ItemParamItem;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ItemService extends BaseService<Item>{

	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@Transactional
	public void save(Item item, String desc,String itemParams) {
		// TODO Auto-generated method stub
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		item.setId(null);
		item.setStatus(1);
		super.save(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(itemDesc.getCreated());
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		this.itemDescService.save(itemDesc);
		
		ItemParamItem itemParamItem=new ItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setId(null);
		itemParamItem.setItemId(item.getId());
		itemParamItem.setUpdated(itemParamItem.getCreated());
		itemParamItem.setParamData(itemParams);
		this.itemParamItemService.save(itemParamItem);
		
	}

	public PageInfo<Item> queryItemList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		Example example = new Example(Item.class);
		example.setOrderByClause("updated DESC");
		List<Item> list = this.itemMapper.selectByExample(example);
		return new PageInfo<>(list);
	}

	@Transactional
	public void upateItem(Item item, String desc,String itemParams,Long itemParamId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//更新商品
				item.setUpdated(new Date());
				item.setCreated(null);
				super.updateSelectiveById(item);
				//更新商品描述
				ItemDesc itemDesc=new ItemDesc();
				itemDesc.setItemId(item.getId());
				itemDesc.setItemDesc(desc);
				itemDesc.setUpdated(new Date());
				this.itemDescService.updateSelectiveById(itemDesc);
				
				ItemParamItem itemParamItem=new ItemParamItem();
				itemParamItem.setId(itemParamId);
				itemParamItem.setUpdated(itemParamItem.getCreated());
				itemParamItem.setParamData(itemParams);
				this.itemParamItemService.updateSelectiveById(itemParamItem);
	}

	@Transactional
	public void deleteByListIds(List<Object> ids) {
		// TODO Auto-generated method stub
		super.deleteByIds(Item.class, ids);
		this.itemDescService.deleteByIds(ids);
		this.itemParamItemService.deleteByIds(ids);
	}

}
