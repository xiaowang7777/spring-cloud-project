package com.wjf.github.userinfoconsumerclient.config;

import com.wjf.github.commons.properties.RabbitConfigProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

//	@Bean
//	public Queue queue() {
//		return new Queue(RabbitConfigProperties.RABBIT_MQ_QUEUE, true);
//	}
//
//	@Bean
//	public TopicExchange topicExchange() {
//		return new TopicExchange(RabbitConfigProperties.RABBIT_MQ_QUEUE);
//	}
//
//	@Bean
//	public Binding binding() {
//		return BindingBuilder.bind(queue()).to(topicExchange()).with(RabbitConfigProperties.RABBIT_MQ_EXCHANGE);
//	}

}
