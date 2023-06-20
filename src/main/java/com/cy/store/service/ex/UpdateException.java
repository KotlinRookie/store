package com.cy.store.service.ex;


/**
 * 更新密码时产生未知的异常
 * @author BP-changxiaolong
 *
 */
public class UpdateException extends ServiceException{

	public UpdateException() {
		super();
	}

	public UpdateException(String message) {
		super(message);
	}
	
	public UpdateException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public UpdateException(Throwable cause) {
		super(cause);
	}
	
	public UpdateException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
	
}
