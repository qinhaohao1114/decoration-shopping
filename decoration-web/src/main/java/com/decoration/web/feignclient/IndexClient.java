package com.decoration.web.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.bean.EasyUIResult;


@FeignClient("decoration-manage")
public interface IndexClient {

	@RequestMapping(value = "/rest/content", method = RequestMethod.GET)
	public EasyUIResult queryContentList(
			@RequestParam("categoryId") Long categoryId,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows) ;
}
