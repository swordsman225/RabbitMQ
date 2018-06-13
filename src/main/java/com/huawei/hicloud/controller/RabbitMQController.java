package com.huawei.hicloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.hicloud.common.ResponseResult;
import com.huawei.hicloud.modules.rabbitmq.constants.IRabbitMQConstants;

@RestController
public class RabbitMQController {

	private Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping(value = "/rabbitmq/send", method = RequestMethod.GET)
	public ResponseResult sendRabbitMQMessage(String msg, String routingKey) {
		logger.info("RabbitMQ send: " + msg + ", routingKey: " + routingKey);

		// message
		Message message = new Message(msg.getBytes(), new MessageProperties());

		/* direct */
		rabbitTemplate.convertAndSend(IRabbitMQConstants.EXCHANGE_DIRECT, IRabbitMQConstants.ROUTING_KEY_DIRECT,
				message);

		/* topic */
		// rabbitTemplate.convertAndSend(IRabbitMQConstants.EXCHANGE_TOPIC, routingKey,
		// message);

		/**
		 * fanout fanout模式,routingKey无效
		 */
		// rabbitTemplate.convertAndSend(IRabbitMQConstants.EXCHANGE_FANOUT, "",
		// message);

		return ResponseResult.build(200, "Send a message to RabbitMQ broker.", msg);
	}

}
