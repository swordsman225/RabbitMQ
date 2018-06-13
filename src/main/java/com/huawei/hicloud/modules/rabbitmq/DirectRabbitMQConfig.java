package com.huawei.hicloud.modules.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huawei.hicloud.modules.rabbitmq.constants.IRabbitMQConstants;

@Configuration
public class DirectRabbitMQConfig {

	/** 
	 * queue 
	 */
	@Bean(name = "queue.direct")
	public Queue queue() {
		return new Queue(IRabbitMQConstants.QUEUE_DIRECT, false, false, false, null);
	}

	/**
	 * direct exchange
	 */
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(IRabbitMQConstants.EXCHANGE_DIRECT, false, false, null);
	}

	@Bean
	public Binding bindExchange(@Qualifier("queue.direct") Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(IRabbitMQConstants.ROUTING_KEY_DIRECT);
	}
}
