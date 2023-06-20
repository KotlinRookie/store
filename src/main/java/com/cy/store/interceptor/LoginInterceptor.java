package com.cy.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 定义一个拦截器的实现类
 * @author BP-changxiaolong
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 检测全局session对象中是否有uid数据，如果有，则放行。如果没有，则重定向到登录界面
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param handler 处理器(url + controller:映射)
	 * @return 如果返回值为true表示放行当前的请求，如果为false则表示拦截
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 通过 HttpServletRequest 对象来获取 session 对象
		Object obj = request.getSession().getAttribute("uid");
		if(obj == null) { // 如果obj(uid的值)为null,说明用户未登录,则重定向到login页面
			response.sendRedirect("/web/login.html");
			// 结束后续的调用
			return false;
		}
		return true;
	}
}
