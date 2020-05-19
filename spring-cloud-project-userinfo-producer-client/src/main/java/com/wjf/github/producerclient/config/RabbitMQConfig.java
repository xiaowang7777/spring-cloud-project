package com.wjf.github.producerclient.config;

import com.wjf.github.commons.properties.RabbitConfigProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Autowired
	private RabbitMQListener rabbitMQListener;

	@Autowired
	private CachingConnectionFactory cachingConnectionFactory;

	@Bean
	public Queue queue() {
		return new Queue(RabbitConfigProperties.RABBIT_MQ_QUEUE, true);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(RabbitConfigProperties.RABBIT_MQ_QUEUE);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(topicExchange()).with(RabbitConfigProperties.RABBIT_MQ_EXCHANGE);
	}

	@Bean
	//设置MQ监听器
	public SimpleMessageListenerContainer simpleMessageListenerContainer(){
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
		container.setMaxConcurrentConsumers(1);
		container.setConcurrentConsumers(1);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setQueueNames(RabbitConfigProperties.RABBIT_MQ_QUEUE);
		container.setMessageListener(rabbitMQListener);
		return container;
	}

}
