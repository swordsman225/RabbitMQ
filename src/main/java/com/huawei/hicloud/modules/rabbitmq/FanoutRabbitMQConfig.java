package com.huawei.hicloud.modules.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huawei.hicloud.modules.rabbitmq.constants.IRabbitMQConstants;

@Configuration
public class FanoutRabbitMQConfig {

	/** topic queue */
	@Bean(name = "queueFanout01")
	public Queue queueFanout01() {
		return new Queue(IRabbitMQConstants.QUEUE_FANOUT_01, false, false, false, null);
	}

	@Bean(name = "queueFanout02")
	public Queue queueFanout02() {
		return new Queue(IRabbitMQConstants.QUEUE_FANOUT_02, false, false, false, null);
	}

	@Bean(name = "queueFanout03")
	public Queue queueFanout03() {
		return new Queue(IRabbitMQConstants.QUEUE_FANOUT_03, false, false, false, null);
	}

	/**
	 * fanout exchange
	 */
	@Bean
	public FanoutExchange fanoutExchange() {
		FanoutExchange exchange = new FanoutExchange(IRabbitMQConstants.EXCHANGE_FANOUT, false, false, null);
		return exchange;
	}

	/**
	 * binding fanout
	 */
	@Bean
	public Binding bindingFanoutExchange01(@Qualifier("queueFanout01") Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public Binding bindingFanoutExchange02(@Qualifier("queueFanout02") Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public Binding bindingFanoutExchange03(@Qualifier("queueFanout03") Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

}
