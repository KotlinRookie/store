package com.cy.store.service.ex;


/**
 * 用户密码不匹配的异常
 * @author BP-changxiaolong
 *
 */
public class PasswordNotMatchException extends ServiceException{

	public PasswordNotMatchException() {
		super();
	}

	public PasswordNotMatchException(String message) {
		super(message);
	}
	
	public PasswordNotMatchException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public PasswordNotMatchException(Throwable cause) {
		super(cause);
	}
	
	public PasswordNotMatchException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
	
}
