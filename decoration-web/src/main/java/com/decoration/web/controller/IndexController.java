package com.decoration.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.decoration.web.service.IndexSerivce;

@Controller
public class IndexController {
	@Autowired
	private IndexSerivce indexSerivce;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(){
		
		ModelAndView mv=new ModelAndView("index");
		//设置大广告位数据
		mv.addObject("indexAdl", this.indexSerivce.getIndexAd1());
		//小广告
		mv.addObject("indexAd2", this.indexSerivce.getIndexAd2());
		return mv;
		
	}
}
