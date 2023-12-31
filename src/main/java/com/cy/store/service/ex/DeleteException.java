package com.cy.store.service.ex;
/**
 * 删除数据所产生的异常
 * @author BP-changxiaolong
 *
 */
public class DeleteException extends ServiceException {
	public DeleteException() {
		super();
	}

	public DeleteException(String message) {
		super(message);
	}
	
	public DeleteException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public DeleteException(Throwable cause) {
		super(cause);
	}
	
	public DeleteException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
