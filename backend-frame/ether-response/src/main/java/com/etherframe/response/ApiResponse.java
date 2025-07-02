package com.etherframe.response;

import java.time.LocalDateTime;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一API响应结果类
 * 
 * @author EtherFrame
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    
    /**
     * 状态码
     */
    private Integer code;
    
    /**
     * 返回消息
     */
    private String message;
    
    /**
     * 返回数据
     */
    private T data;
    
    /**
     * 时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    /**
     * 请求ID，用于链路追踪
     */
    private String requestId;
    
    /**
     * 服务名称
     */
    private String service;
    
    /**
     * 执行时长（毫秒）
     */
    private Long duration;
    
    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
    
    /**
     * 成功返回
     */
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage());
    }
    
    /**
     * 成功返回带数据
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }
    
    /**
     * 成功返回带消息
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), message);
    }
    
    /**
     * 成功返回带消息和数据
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ErrorCode.SUCCESS.getCode(), message, data);
    }
    
    /**
     * 失败返回
     */
    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }
    
    /**
     * 失败返回带消息
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }
    
    /**
     * 失败返回带状态码和消息
     */
    public static <T> ApiResponse<T> error(Integer code, String message) {
        return new ApiResponse<>(code, message);
    }
    
    /**
     * 失败返回带ErrorCode
     */
    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage());
    }
    
    /**
     * 失败返回带ErrorCode和数据
     */
    public static <T> ApiResponse<T> error(ErrorCode errorCode, T data) {
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), data);
    }
    
    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return ErrorCode.SUCCESS.getCode().equals(this.code);
    }
    
    /**
     * 设置请求ID
     */
    public ApiResponse<T> requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
    
    /**
     * 设置服务名称
     */
    public ApiResponse<T> service(String service) {
        this.service = service;
        return this;
    }
    
    /**
     * 设置执行时长
     */
    public ApiResponse<T> duration(Long duration) {
        this.duration = duration;
        return this;
    }
} 