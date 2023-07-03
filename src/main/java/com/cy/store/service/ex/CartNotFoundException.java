package com.cy.store.service.ex;
/**
 * 没有查询到购物车数据的异常
 * @author BP-changxiaolong
 *
 */
public class CartNotFoundException extends ServiceException {
	public CartNotFoundException() {
		super();
	}

	public CartNotFoundException(String message) {
		super(message);
	}
	
	public CartNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public CartNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public CartNotFoundException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
