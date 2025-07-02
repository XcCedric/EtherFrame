package com.etherframe.rabbitmq.base;

import java.util.Queue;

import javax.naming.Binding;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * RabbitMQ基础监听器
 * 
 * @author EtherFrame
 */
@Slf4j
public abstract class BaseMessageListener {
    
    @Autowired
    private AmqpAdmin amqpAdmin;
    
    /**
     * 获取队列名称，默认使用类全限定名
     */
    protected String getQueueName() {
        return this.getClass().getName();
    }
    
    /**
     * 获取交换机名称，默认使用队列名称
     */
    protected String getExchangeName() {
        return getQueueName() + ".exchange";
    }
    
    /**
     * 获取路由键，默认使用队列名称
     */
    protected String getRoutingKey() {
        return getQueueName() + ".routing.key";
    }
    
    /**
     * 获取死信交换机名称
     */
    protected String getDeadLetterExchange() {
        return getQueueName() + ".dlx";
    }
    
    /**
     * 获取死信队列名称
     */
    protected String getDeadLetterQueue() {
        return getQueueName() + ".dlx.queue";
    }
    
    /**
     * 初始化队列和交换机
     */
    public void initializeQueue() {
        try {
            // 创建死信交换机
            DirectExchange deadLetterExchange = new DirectExchange(getDeadLetterExchange(), true, false);
            amqpAdmin.declareExchange(deadLetterExchange);
            
            // 创建死信队列
            Queue deadLetterQueue = QueueBuilder.durable(getDeadLetterQueue()).build();
            amqpAdmin.declareQueue(deadLetterQueue);
            
            // 绑定死信队列到死信交换机
            Binding deadLetterBinding = BindingBuilder.bind(deadLetterQueue)
                    .to(deadLetterExchange)
                    .with(getRoutingKey());
            amqpAdmin.declareBinding(deadLetterBinding);
            
            // 创建主交换机
            DirectExchange exchange = new DirectExchange(getExchangeName(), true, false);
            amqpAdmin.declareExchange(exchange);
            
            // 创建主队列（配置死信交换机）
            Queue queue = QueueBuilder.durable(getQueueName())
                    .withArgument("x-dead-letter-exchange", getDeadLetterExchange())
                    .withArgument("x-dead-letter-routing-key", getRoutingKey())
                    .build();
            amqpAdmin.declareQueue(queue);
            
            // 绑定主队列到主交换机
            Binding binding = BindingBuilder.bind(queue)
                    .to(exchange)
                    .with(getRoutingKey());
            amqpAdmin.declareBinding(binding);
            
            log.info("队列初始化成功: {}", getQueueName());
            
        } catch (Exception e) {
            log.error("队列初始化失败: {}", getQueueName(), e);
        }
    }
    
    /**
     * 处理消息的抽象方法，子类必须实现
     * 
     * @param message 消息内容
     */
    public abstract void handleMessage(String message);
    
    /**
     * 处理消息失败时的回调
     * 
     * @param message 消息内容
     * @param exception 异常信息
     */
    protected void onMessageFailed(String message, Exception exception) {
        log.error("消息处理失败: {}, 消息内容: {}", getQueueName(), message, exception);
    }
} 