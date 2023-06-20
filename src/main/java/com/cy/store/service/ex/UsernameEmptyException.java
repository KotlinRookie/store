package com.cy.store.service.ex;
/**
 * 用户名为空的异常
 * @author BP-changxiaolong
 *
 */
public class UsernameEmptyException extends ServiceException{
	public UsernameEmptyException() {
		super();
	}

	public UsernameEmptyException(String message) {
		super(message);
	}
	
	public UsernameEmptyException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public UsernameEmptyException(Throwable cause) {
		super(cause);
	}
	
	public UsernameEmptyException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
