package com.etherframe.exception;

/**
 * 认证异常类
 * 
 * @author EtherFrame
 */
public class AuthenticationException extends BaseException {
    
    public AuthenticationException() {
        super(ErrorCode.UNAUTHORIZED);
    }
    
    public AuthenticationException(String message) {
        super(ErrorCode.UNAUTHORIZED.getCode(), message);
    }
    
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
    
    public AuthenticationException(String message, Throwable cause) {
        super(ErrorCode.UNAUTHORIZED.getCode(), message, cause);
    }
    
    public AuthenticationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
} 