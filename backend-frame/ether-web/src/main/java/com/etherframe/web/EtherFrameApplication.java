package com.etherframe.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * EtherFrame基础启动类
 * 业务服务可以继承此类或使用@ComponentScan扫描EtherFrame包
 * 
 * @author EtherFrame
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.etherframe"
})
public class EtherFrameApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EtherFrameApplication.class, args);
    }
} 