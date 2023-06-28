package com.cy.store.service.ex;
/**
 * 收货地址不存在所产生的异常
 * @author BP-changxiaolong
 *
 */
public class AddressNotFountException extends ServiceException {
	public AddressNotFountException() {
		super();
	}

	public AddressNotFountException(String message) {
		super(message);
	}
	
	public AddressNotFountException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public AddressNotFountException(Throwable cause) {
		super(cause);
	}
	
	public AddressNotFountException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
