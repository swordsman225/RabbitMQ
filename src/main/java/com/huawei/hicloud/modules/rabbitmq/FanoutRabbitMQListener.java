package com.huawei.hicloud.modules.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.huawei.hicloud.modules.rabbitmq.constants.IRabbitMQConstants;
import com.rabbitmq.client.Channel;

@Component
public class FanoutRabbitMQListener {
	private Logger logger = LoggerFactory.getLogger(FanoutRabbitMQListener.class);

	@RabbitListener(queues = IRabbitMQConstants.QUEUE_FANOUT_01)
	public void onMessage_01(Message message, Channel channel) {
		logger.info("### message: " + JSON.toJSONString(message) + ", channel: " + JSON.toJSONString(channel));
		logger.info("### Fanout RabbitMQ " + IRabbitMQConstants.QUEUE_FANOUT_01 + " receive: "
				+ new String(message.getBody()));
	}

	@RabbitListener(queues = IRabbitMQConstants.QUEUE_FANOUT_02)
	public void onMessage_02(Message message, Channel channel) {
		logger.info("### message: " + JSON.toJSONString(message) + ", channel: " + JSON.toJSONString(channel));
		logger.info("### Fanout RabbitMQ " + IRabbitMQConstants.QUEUE_FANOUT_02 + " receive: "
				+ new String(message.getBody()));
	}

	@RabbitListener(queues = IRabbitMQConstants.QUEUE_FANOUT_03)
	public void onMessage_03(Message message, Channel channel) {
		logger.info("### message: " + JSON.toJSONString(message) + ", channel: " + JSON.toJSONString(channel));
		logger.info("### Fanout RabbitMQ " + IRabbitMQConstants.QUEUE_FANOUT_03 + " receive: "
				+ new String(message.getBody()));
	}
}
