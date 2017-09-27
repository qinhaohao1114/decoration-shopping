package com.decoration.manage.config;


import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.decoration.common.spring.exetend.converter.json.CallbackMappingJackson2HttpMessageConverter;
import com.jolbox.bonecp.BoneCPDataSource;

@SpringBootApplication
@ComponentScan("com.decoration.manage")
@PropertySource(value = { "classpath:jdbc.properties", "classpath:env.properties"}, ignoreResourceNotFound = true)
@Configuration
@EnableEurekaClient
public class DecorationConfig extends SpringBootServletInitializer{

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.driver}")
	private String jdbcDriverClassName;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
		// 数据库驱动
		boneCPDataSource.setDriverClass(jdbcDriverClassName);
		// 相应驱动的jdbcUrl
		boneCPDataSource.setJdbcUrl(jdbcUrl);
		// 数据库的用户名
		boneCPDataSource.setUsername(jdbcUsername);
		// 数据库的密码
		boneCPDataSource.setPassword(jdbcUsername);
		// 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
		boneCPDataSource.setIdleConnectionTestPeriodInMinutes(60);
		// 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0
		boneCPDataSource.setIdleMaxAgeInMinutes(30);
		// 每个分区最大的连接数
		boneCPDataSource.setMaxConnectionsPerPartition(100);
		// 每个分区最小的连接数
		boneCPDataSource.setMinConnectionsPerPartition(5);
		return boneCPDataSource;
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}
	
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
//		
//		return new CallbackMappingJackson2HttpMessageConverter();
//	}
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(1);
		return internalResourceViewResolver;
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DecorationConfig.class);
    }

}
