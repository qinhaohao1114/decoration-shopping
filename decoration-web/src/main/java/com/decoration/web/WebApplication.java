package com.decoration.web;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan("com.decoration.web")
@PropertySource(value = { "classpath:env.properties" }, ignoreResourceNotFound = true)
public class WebApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(1);
		return internalResourceViewResolver;
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebApplication.class);
	}
}
