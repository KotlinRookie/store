package com.cy.store.service.ex;


/**
 * 用户收货地址超过界限的异常
 * @author BP-changxiaolong
 *
 */
public class AddressCountLimitException extends ServiceException{

	public AddressCountLimitException() {
		super();
	}

	public AddressCountLimitException(String message) {
		super(message);
	}
	
	public AddressCountLimitException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public AddressCountLimitException(Throwable cause) {
		super(cause);
	}
	
	public AddressCountLimitException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
	
}
