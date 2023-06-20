package com.cy.store.service.ex;
/**
 * 验证密码的异常类
 * @author BP-changxiaolong
 *
 */
public class UserPasswordException extends ServiceException{
	public UserPasswordException() {
		super();
	}

	public UserPasswordException(String message) {
		super(message);
	}
	
	public UserPasswordException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public UserPasswordException(Throwable cause) {
		super(cause);
	}
	
	public UserPasswordException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
