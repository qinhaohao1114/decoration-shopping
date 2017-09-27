package com.decoration.manage.config;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.decoration.common.spring.exetend.converter.json.CallbackMappingJackson2HttpMessageConverter;

@Configuration //申明这是一个配置
public class MySrpingMVCConfig extends WebMvcConfigurerAdapter{

    // 自定义拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
//            @Override
//            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//                    throws Exception {
//                System.out.println("自定义拦截器............");
//                return true;
//            }
//            
//            @Override
//            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                    ModelAndView modelAndView) throws Exception {
//                
//            }
//            
//            @Override
//            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//                    Exception ex) throws Exception {
//            }
//        };
//        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
//    }

    // 自定义消息转化器的第二种方法
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    	System.out.println("自定义消息转化器*********");
//    	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter=new CallbackMappingJackson2HttpMessageConverter();
//        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        converters.add(mappingJackson2HttpMessageConverter);
//        converters.add(converter);
//        
//    }

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
//		super.extendMessageConverters(converters);
		System.out.println("自定义消息转化器*********");
    	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter=new CallbackMappingJackson2HttpMessageConverter();
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(0,converter);
        converters.add(1,mappingJackson2HttpMessageConverter);
        for (HttpMessageConverter<?> messageConverter : converters) {
            System.out.println(messageConverter); //2
        }
	}
    

}