package com.huawei.hicloud.modules.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huawei.hicloud.modules.rabbitmq.constants.IRabbitMQConstants;

@Configuration
public class TopicRabbitMQConfig {
	/** topic queue */
	@Bean(name = "queueTopic01")
	public Queue queueMessage() {
		return new Queue(IRabbitMQConstants.QUEUE_TOPIC_01, false, false, false, null);
	}

	@Bean(name = "queueTopic02")
	public Queue queueMessages() {
		return new Queue(IRabbitMQConstants.QUEUE_TOPIC_02, false, false, false, null);
	}

	/** topic exchange */
	@Bean
	public TopicExchange exchangeTopic() {
		TopicExchange exchange = new TopicExchange(IRabbitMQConstants.EXCHANGE_TOPIC, false, false, null);
		return exchange;
	}

	/** binding topic */
	@Bean
	public Binding bindingExchangeMessage(@Qualifier("queueTopic01") Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(IRabbitMQConstants.ROUTING_KEY_TOPIC_01);
	}

	@Bean
	public Binding bindingExchangeMessages(@Qualifier("queueTopic02") Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(IRabbitMQConstants.ROUTING_KEY_TOPIC_02);
	}

}
