package com.cy.store.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cy.store.interceptor.LoginInterceptor;

/**
 * 处理器拦截器的注册类
 * @author BP-changxiaolong
 *
 */
//拦截器的配置注解 @Configuration，不加的话拦截器没有反应
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer{

	// 将自定义的拦截器（LoginInterceptor）进行注册
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 创建自定义的拦截器对象
		HandlerInterceptor interceptor = new LoginInterceptor();
		
		// 定义白名单List集合
		List<String> patterns = new ArrayList<>();
		patterns.add("/bootstrap3/**");
		patterns.add("/css/**");
		patterns.add("/images/**");
		patterns.add("/js/**");
		patterns.add("/web/register.html");
		patterns.add("/web/login.html");
		patterns.add("/web/index.html");
		patterns.add("/web/product.html");
		patterns.add("/users/reg");
		patterns.add("/users/login");
		patterns.add("/districts/**");
		patterns.add("/products/**");
		
		// 完成拦截器的注册
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**") // 表示要拦截的url是什么
			.excludePathPatterns(patterns);// 表示要放行的url，需要List集合	
	}
}
