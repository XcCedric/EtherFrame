package com.etherframe.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页响应结果类
 * 
 * @author EtherFrame
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    
    /**
     * 数据列表
     */
    private List<T> records;
    
    /**
     * 总记录数
     */
    private Long total;
    
    /**
     * 当前页码
     */
    private Long current;
    
    /**
     * 每页大小
     */
    private Long size;
    
    /**
     * 总页数
     */
    private Long pages;
    
    /**
     * 是否有下一页
     */
    private Boolean hasNext;
    
    /**
     * 是否有上一页
     */
    private Boolean hasPrevious;
    
    public PageResponse(List<T> records, Long total, Long current, Long size) {
        this.records = records;
        this.total = total;
        this.current = current;
        this.size = size;
        this.pages = calculatePages(total, size);
        this.hasNext = current < pages;
        this.hasPrevious = current > 1;
    }
    
    /**
     * 计算总页数
     */
    private Long calculatePages(Long total, Long size) {
        if (total == null || size == null || size == 0) {
            return 0L;
        }
        return (total + size - 1) / size;
    }
    
    /**
     * 创建空分页结果
     */
    public static <T> PageResponse<T> empty() {
        return new PageResponse<>(List.of(), 0L, 1L, 10L);
    }
    
    /**
     * 创建空分页结果带参数
     */
    public static <T> PageResponse<T> empty(Long current, Long size) {
        return new PageResponse<>(List.of(), 0L, current, size);
    }
    
    /**
     * 从其他分页对象转换
     */
    public static <T> PageResponse<T> of(List<T> records, Long total, Long current, Long size) {
        return new PageResponse<>(records, total, current, size);
    }
} 