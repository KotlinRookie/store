package com.cy.store.service.ex;
/**
 * 没有找到商品数据的异常
 * @author BP-changxiaolong
 *
 */
public class ProductNotFoundException extends ServiceException {
	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public ProductNotFoundException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
