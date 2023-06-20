package com.cy.store.controller.ex;
/**
 * 上传文件大小为空的异常
 * @author BP-changxiaolong
 *
 */
public class FileSizeException extends FileUploadException{
    public FileSizeException() {
        super();
    }
    
    public FileSizeException(String message) {
        super(message);
    }
    
    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FileSizeException(Throwable cause) {
        super(cause);
    }

    protected FileSizeException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
