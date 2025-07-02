package com.etherframe.exception;

/**
 * 参数验证异常类
 * 
 * @author EtherFrame
 */
public class ValidationException extends BaseException {
    
    public ValidationException() {
        super(ErrorCode.UNPROCESSABLE_ENTITY);
    }
    
    public ValidationException(String message) {
        super(ErrorCode.UNPROCESSABLE_ENTITY.getCode(), message);
    }
    
    public ValidationException(String message, Object data) {
        super(ErrorCode.UNPROCESSABLE_ENTITY.getCode(), message, data);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(ErrorCode.UNPROCESSABLE_ENTITY.getCode(), message, cause);
    }
} 