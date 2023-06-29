package com.cy.store.controller;
/**
 * 控制层类的基类
 * @author BP-changxiaolong
 *
 */


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileStateException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.controller.ex.FileUploadException;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.AddressNotFountException;
import com.cy.store.service.ex.DeleteException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.ProductNotFoundException;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UserPasswordException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.service.ex.UsernameEmptyException;
import com.cy.store.util.JsonResult;

public class BaseController {
	/**
	 * 操作成功的状态码
	 */
	public static final int OK = 200;
	
	//请求处理方法，这个方法的返回值就是需要传递给前端的数据
	//自动将异常对象传递给此方法的参数列表上
	//当前项目中产生了异常，被统一拦截到此方法中，这个方法此时就是请求处理方法，方法的返回值直接返回给前端
	
	//@ExceptionHandler用于统一处理抛出的异常
	//※※※凡是抛出的异常是 ServiceException 或 FileUploadException 都会被拦截到ExceptionHandler
	@ExceptionHandler({ServiceException.class,FileUploadException.class})// 用于统一处理抛出的异常
	public JsonResult<Void> handleException(Throwable e){
		JsonResult<Void> result = new JsonResult<>(e);
		if(e instanceof UsernameDuplicatedException) {
			result.setState(4000);
			result.setMessage("用户名已经被占用");
		}else if(e instanceof UsernameEmptyException) {
			result.setState(4001);
			result.setMessage("用户名不能为空");
		}else if(e instanceof UserPasswordException){
			result.setState(4002);
			result.setMessage("用户密码不符合规范");
		}else if(e instanceof AddressCountLimitException){
			result.setState(4003);
			result.setMessage("用户收货地址超标的异常");
		}else if(e instanceof AddressNotFountException){
			result.setState(4004);
			result.setMessage("用户收货地址数据不存在");
		}else if(e instanceof AccessDeniedException){
			result.setState(4005);
			result.setMessage("收货地址数据非法访问的异常");
		}else if(e instanceof ProductNotFoundException){
			result.setState(4006);
			result.setMessage("商品数据不存在的异常");
		}else if(e instanceof InsertException) {
			result.setState(5000);
			result.setMessage("注册时产生未知的异常");
		}else if(e instanceof UserNotFoundException){
			result.setState(5001);
			result.setMessage("用户数据不存在");
		}else if(e instanceof PasswordNotMatchException){
			result.setState(5002);
			result.setMessage("密码错误");
		}else if(e instanceof UpdateException){
			result.setState(5003);
			result.setMessage("更新数据时产生未知的异常");
		}else if(e instanceof DeleteException){
			result.setState(5004);
			result.setMessage("删除数据时产生未知的异常");
		}else if(e instanceof FileEmptyException){
			result.setState(6000);
			result.setMessage("文件为空的异常");
		}else if(e instanceof FileSizeException){
			result.setState(6001);
			result.setMessage("文件大小为空的异常");
		}else if(e instanceof FileTypeException){
			result.setState(6002);
			result.setMessage("文件类型的异常");
		}else if(e instanceof FileStateException){
			result.setState(6003);
			result.setMessage("文件State的异常");
		}else if(e instanceof FileUploadException){
			result.setState(6004);
			result.setMessage("文件上传的异常");
		}
		return result;
	}
	
	/**
	 * 获取session对象中的uid
	 * @param session session对象
	 * @return 当前登录的用户uid的值
	 */
	protected final Integer getuidFromSession(HttpSession session) {
		return Integer.valueOf
				(session.getAttribute("uid").toString());
	}
	
	/**
	 * 获取当前登录用户的username
	 * @param session session对象
	 * @return 当前登录的用户名
	 */
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
}
