package com.etherframe.exception;

import com.etherframe.exception.code.ErrorCode;

/**
 * 业务异常类
 * 
 * @author EtherFrame
 */
public class BusinessException extends BaseException {
    
    public BusinessException() {
        super(ErrorCode.BUSINESS_ERROR);
    }
    
    public BusinessException(String message) {
        super(ErrorCode.BUSINESS_ERROR.getCode(), message);
    }
    
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(ErrorCode.BUSINESS_ERROR.getCode(), message, cause);
    }
    
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
    
    public BusinessException(ErrorCode errorCode, Object data) {
        super(errorCode, data);
    }
} 