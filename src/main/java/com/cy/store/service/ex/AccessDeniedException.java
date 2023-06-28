package com.cy.store.service.ex;
/**
 * 非法访问所产生的异常
 * @author BP-changxiaolong
 *
 */
public class AccessDeniedException extends ServiceException {
	public AccessDeniedException() {
		super();
	}

	public AccessDeniedException(String message) {
		super(message);
	}
	
	public AccessDeniedException(String message,Throwable cause) {
		super(message,cause);
	}
	
	public AccessDeniedException(Throwable cause) {
		super(cause);
	}
	
	public AccessDeniedException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}
