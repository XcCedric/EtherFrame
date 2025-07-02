package com.etherframe.common.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.TypeReference;

import lombok.extern.slf4j.Slf4j;

/**
 * JSON工具类
 * 
 * @author EtherFrame
 */
@Slf4j
public class JsonUtil {
    
    /**
     * 对象转JSON字符串
     */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return JSON.toJSONString(obj);
        } catch (JSONException e) {
            log.error("对象转JSON字符串失败", e);
            return null;
        }
    }
    
    /**
     * JSON字符串转对象
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        try {
            return JSON.parseObject(jsonString, clazz);
        } catch (JSONException e) {
            log.error("JSON字符串转对象失败", e);
            return null;
        }
    }
    
    /**
     * JSON字符串转对象（泛型）
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> typeReference) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        try {
            return JSON.parseObject(jsonString, typeReference);
        } catch (JSONException e) {
            log.error("JSON字符串转对象失败", e);
            return null;
        }
    }
    
    /**
     * JSON字符串转List
     */
    public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        try {
            return JSON.parseArray(jsonString, clazz);
        } catch (JSONException e) {
            log.error("JSON字符串转List失败", e);
            return null;
        }
    }
    
    /**
     * JSON字符串转Map
     */
    public static Map<String, Object> parseMap(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }
        try {
            return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (JSONException e) {
            log.error("JSON字符串转Map失败", e);
            return null;
        }
    }
    
    /**
     * 对象转Map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            String jsonString = toJsonString(obj);
            return parseMap(jsonString);
        } catch (Exception e) {
            log.error("对象转Map失败", e);
            return null;
        }
    }
    
    /**
     * Map转对象
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            String jsonString = toJsonString(map);
            return parseObject(jsonString, clazz);
        } catch (Exception e) {
            log.error("Map转对象失败", e);
            return null;
        }
    }
    
    /**
     * 判断字符串是否为有效的JSON
     */
    public static boolean isValidJson(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return false;
        }
        try {
            JSON.parse(jsonString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
} 