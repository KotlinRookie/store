package com.cy.store.controller.ex;
/**
 * 文件读写的异常
 * @author BP-changxiaolong
 *
 */
public class FileUploadIOException extends FileUploadException{
    public FileUploadIOException() {
        super();
    }
    
    public FileUploadIOException(String message) {
        super(message);
    }
    
    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    protected FileUploadIOException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
