package com.huawei.hicloud.modules.rabbitmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.huawei.hicloud.modules.rabbitmq.constants.IRabbitMQConstants;
import com.rabbitmq.client.Channel;

@Component
public class DirectRabbitMQListener {
	
	private Logger logger = LoggerFactory.getLogger(DirectRabbitMQListener.class);

	/*
	 * @RabbitHandler public void onMessage(@Payload String msg) {
	 * logger.info("Direct exchange receive: " + msg); }
	 */

	@RabbitListener(queues = IRabbitMQConstants.QUEUE_DIRECT)
	public void onMessage(Message message, Channel channel) throws IOException {
		logger.info("### message: " + JSON.toJSONString(message) + ", channel: " + JSON.toJSONString(channel));
		logger.info("### Direct exchange receive: " + new String(message.getBody()));
		//channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
	}

}
