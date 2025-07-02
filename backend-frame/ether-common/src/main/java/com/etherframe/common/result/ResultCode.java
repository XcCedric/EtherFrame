package com.etherframe.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一结果状态码枚举
 * 
 * @author EtherFrame
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    
    // 成功
    SUCCESS(200, "操作成功"),
    
    // 客户端错误 4xx
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    CONFLICT(409, "资源冲突"),
    UNPROCESSABLE_ENTITY(422, "请求参数验证失败"),
    TOO_MANY_REQUESTS(429, "请求过于频繁"),
    
    // 服务器错误 5xx
    ERROR(500, "系统内部错误"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    
    // 业务错误 6xxx
    BUSINESS_ERROR(6000, "业务处理失败"),
    DATA_NOT_FOUND(6001, "数据不存在"),
    DATA_ALREADY_EXISTS(6002, "数据已存在"),
    DATA_INVALID(6003, "数据无效"),
    OPERATION_NOT_ALLOWED(6004, "操作不被允许"),
    
    // 认证授权错误 7xxx
    LOGIN_FAILED(7001, "登录失败"),
    ACCOUNT_DISABLED(7002, "账户已禁用"),
    ACCOUNT_LOCKED(7003, "账户已锁定"),
    PASSWORD_EXPIRED(7004, "密码已过期"),
    TOKEN_INVALID(7005, "令牌无效"),
    TOKEN_EXPIRED(7006, "令牌已过期"),
    PERMISSION_DENIED(7007, "权限不足"),
    
    // 数据库错误 8xxx
    DATABASE_ERROR(8001, "数据库操作失败"),
    CONNECTION_ERROR(8002, "数据库连接失败"),
    TRANSACTION_ERROR(8003, "事务处理失败"),
    
    // 外部服务错误 9xxx
    EXTERNAL_SERVICE_ERROR(9001, "外部服务调用失败"),
    NETWORK_ERROR(9002, "网络连接失败"),
    TIMEOUT_ERROR(9003, "请求超时"),
    
    // AI服务错误 10xxx
    AI_SERVICE_ERROR(10001, "AI服务异常"),
    AI_MODEL_NOT_FOUND(10002, "AI模型未找到"),
    AI_GENERATION_FAILED(10003, "AI内容生成失败"),
    AI_QUOTA_EXCEEDED(10004, "AI服务配额超限");
    
    /**
     * 状态码
     */
    private final Integer code;
    
    /**
     * 消息
     */
    private final String message;
    
    /**
     * 根据状态码获取ResultCode
     */
    public static ResultCode fromCode(Integer code) {
        for (ResultCode resultCode : values()) {
            if (resultCode.getCode().equals(code)) {
                return resultCode;
            }
        }
        return ERROR; // 默认返回系统错误
    }
    
    /**
     * 判断是否为成功状态
     */
    public boolean isSuccess() {
        return this == SUCCESS;
    }
    
    /**
     * 判断是否为客户端错误
     */
    public boolean isClientError() {
        return code >= 400 && code < 500;
    }
    
    /**
     * 判断是否为服务器错误
     */
    public boolean isServerError() {
        return code >= 500 && code < 600;
    }
    
    /**
     * 判断是否为业务错误
     */
    public boolean isBusinessError() {
        return code >= 6000 && code < 7000;
    }
}
