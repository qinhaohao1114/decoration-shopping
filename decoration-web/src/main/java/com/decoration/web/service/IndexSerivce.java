package com.decoration.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.decoration.web.feignclient.IndexClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.service.ApiService;
import com.taotao.common.service.RedisService;
import com.taotao.manage.pojo.Content;

@Service
public class IndexSerivce {

	@Autowired
	private IndexClient indexClient;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 获取首页大广告位数据，返回json数据
	 * 
	 * @return
	 */
	public String getIndexAd1() {
		// TODO Auto-generated method stub
			
//			String jsonDate = this.indexClient.queryContentList(35L, 1, 6);
//			EasyUIResult easyUIResult = EasyUIResult.formatToList(jsonDate,
//					Content.class);
		try{
		EasyUIResult easyUIResult=this.indexClient.queryContentList(36L, 1, 6);
			List<Content> contents = (List<Content>) easyUIResult.getRows();
			// 封装前台需要的json结构
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (Content content : contents) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("srcB", content.getPic());
				map.put("height", 240);
				map.put("alt", content.getTitle());
				map.put("width", 670);
				map.put("src", content.getPic());
				map.put("href", content.getUrl());
				map.put("heightB", 240);
				result.add(map);
			}
			String resultJson = MAPPER.writeValueAsString(result);
			return resultJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object getIndexAd2() {
		// TODO Auto-generated method stub
		try {
//			String jsonDate = this.apiService.doGet(url);
//			EasyUIResult easyUIResult = EasyUIResult.formatToList(jsonDate,
//					Content.class);
			EasyUIResult easyUIResult=this.indexClient.queryContentList(38L, 1, 6);
			List<Content> contents = (List<Content>) easyUIResult.getRows();
			// 封装前台需要的json结构
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (Content content : contents) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("srcB", content.getPic());
				map.put("height", 70);
				map.put("alt", content.getTitle());
				map.put("width", 310);
				map.put("widthB", 210);
				map.put("src", content.getPic());
				map.put("href", content.getUrl());
				map.put("heightB", 70);
				result.add(map);
			}
			return MAPPER.writeValueAsString(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
