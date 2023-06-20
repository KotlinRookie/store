package com.cy.store.controller.ex;
/**
 * 文件State的异常
 * @author BP-changxiaolong
 *
 */
public class FileStateException extends FileUploadException{
    public FileStateException() {
        super();
    }
    
    public FileStateException(String message) {
        super(message);
    }
    
    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FileStateException(Throwable cause) {
        super(cause);
    }

    protected FileStateException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
