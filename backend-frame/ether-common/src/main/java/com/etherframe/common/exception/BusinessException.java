package com.etherframe.common.exception;

import main.java.com.etherframe.common.result.ResultCode;

/**
 * 业务异常类
 * 
 * @author EtherFrame
 */
public class BusinessException extends BaseException {
    
    public BusinessException() {
        super(ResultCode.BUSINESS_ERROR);
    }
    
    public BusinessException(String message) {
        super(ResultCode.BUSINESS_ERROR.getCode(), message);
    }
    
    public BusinessException(ResultCode resultCode) {
        super(resultCode);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(ResultCode.BUSINESS_ERROR.getCode(), message, cause);
    }
    
    public BusinessException(ResultCode resultCode, Throwable cause) {
        super(resultCode, cause);
    }
} 