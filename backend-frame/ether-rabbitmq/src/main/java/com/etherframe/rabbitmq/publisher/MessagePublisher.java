package com.etherframe.rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息发布工具类
 * 
 * @author EtherFrame
 */
@Slf4j
@Component
public class MessagePublisher {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    /**
     * 发送消息到指定交换机和路由键
     * 
     * @param exchange 交换机名称
     * @param routingKey 路由键
     * @param message 消息内容
     */
    public void sendMessage(String exchange, String routingKey, Object message) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("消息发送成功 - 交换机: {}, 路由键: {}, 消息: {}", exchange, routingKey, message);
        } catch (Exception e) {
            log.error("消息发送失败 - 交换机: {}, 路由键: {}, 消息: {}", exchange, routingKey, message, e);
            throw e;
        }
    }
    
    /**
     * 发送消息到指定队列
     * 
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void sendMessageToQueue(String queueName, Object message) {
        try {
            rabbitTemplate.convertAndSend(queueName, message);
            log.info("消息发送成功 - 队列: {}, 消息: {}", queueName, message);
        } catch (Exception e) {
            log.error("消息发送失败 - 队列: {}, 消息: {}", queueName, message, e);
            throw e;
        }
    }
    
    /**
     * 延迟发送消息
     * 
     * @param exchange 交换机名称
     * @param routingKey 路由键
     * @param message 消息内容
     * @param delayMillis 延迟时间（毫秒）
     */
    public void sendDelayedMessage(String exchange, String routingKey, Object message, long delayMillis) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message, msg -> {
                msg.getMessageProperties().setDelay((int) delayMillis);
                return msg;
            });
            log.info("延迟消息发送成功 - 交换机: {}, 路由键: {}, 延迟: {}ms, 消息: {}", 
                    exchange, routingKey, delayMillis, message);
        } catch (Exception e) {
            log.error("延迟消息发送失败 - 交换机: {}, 路由键: {}, 延迟: {}ms, 消息: {}", 
                    exchange, routingKey, delayMillis, message, e);
            throw e;
        }
    }
} 