package com.huawei.hicloud;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQProducerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducerTest.class);
	
	private static final String QUEUE_NAME_DEFAULT = "QUEUE_DEFAULT";
	private static final String QUEUE_NAME_DIRECT = "QUEUE_DIRECT";
	private static final String QUEUE_NAME_TOPIC1 = "QUEUE_TOPIC1";
	private static final String QUEUE_NAME_TOPIC2 = "QUEUE_TOPIC2";
	private static final String QUEUE_NAME_FANOUT1 = "QUEUE_FANOUT1";
	private static final String QUEUE_NAME_FANOUT2 = "QUEUE_FANOUT2";
	
	private static final String EXCHANGE_NAME_DIRECT = "EXCHANGE_DIRECT";
	private static final String EXCHANGE_NAME_TOPIC = "EXCHANGE_TOPIC";
	private static final String EXCHANGE_NAME_FANOUT = "EXCHANGE_FANOUT";
	
	private static final String ROUTING_KEY_DIRECT = "rabbit.direct";
	private static final String ROUTING_KEY_TOPIC1 = "*.topic.*";
	private static final String ROUTING_KEY_TOPIC2 = "carrot.topic.*";
	private static final String ROUTING_KEY_FANOUT1 = "*.fanout.*";
	private static final String ROUTING_KEY_FANOUT2 = "carrot.fanout.*";

	//ExchangeTypes
	
	@Test
	public void sendTest() throws IOException, TimeoutException {
		
		logger.info("### RabbitMQ send message test ...");
		
		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		
		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		//channel.basicQos(1);
		
		channel.queueDeclare(QUEUE_NAME_DEFAULT, false, false, false, null);
		
		// 发布消息
		String message = "Hello rabbitMQ!";
		channel.basicPublish("", QUEUE_NAME_DEFAULT, null, message.getBytes());
		
		// 关闭连接
		channel.close();
		connection.close();
		
	}
	
	
	@Test
	public void directSendTest() throws IOException, TimeoutException {
		
		logger.info("### RabbitMQ send message test ...");
		
		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		
		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		//channel.basicQos(1);
		
		// queue声明
		channel.queueDeclare(QUEUE_NAME_DIRECT, false, false, false, null);
		// exchange声明
		channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, BuiltinExchangeType.DIRECT, false, false, null);
		channel.queueBind(QUEUE_NAME_DIRECT, EXCHANGE_NAME_DIRECT, ROUTING_KEY_DIRECT);
		
		
		// 发布消息
		String message = "Hello rabbitMQ! Exchange type is direct!";
		channel.basicPublish(EXCHANGE_NAME_DIRECT, ROUTING_KEY_DIRECT, null, message.getBytes());
		
		// 关闭连接
		channel.close();
		connection.close();
		
	}
	
	@Test
	public void topicSendTest() throws IOException, TimeoutException {
		
		logger.info("### RabbitMQ send message test ...");
		
		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		
		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		//channel.basicQos(1);
		
		// queue声明
		channel.queueDeclare(QUEUE_NAME_TOPIC1, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_TOPIC2, false, false, false, null);
		// exchange声明
		channel.exchangeDeclare(EXCHANGE_NAME_TOPIC, BuiltinExchangeType.TOPIC, false, false, null);
		channel.queueBind(QUEUE_NAME_TOPIC1, EXCHANGE_NAME_TOPIC, ROUTING_KEY_TOPIC1);
		channel.queueBind(QUEUE_NAME_TOPIC2, EXCHANGE_NAME_TOPIC, ROUTING_KEY_TOPIC2);
		
		
		// 发布消息
		String message = "Hello rabbitMQ! Exchange type is topic!";
		channel.basicPublish(EXCHANGE_NAME_TOPIC, "123.topic.123", null, message.getBytes());
		
		// 关闭连接
		channel.close();
		connection.close();
		
	}
	
	@Test
	public void fanoutSendTest() throws IOException, TimeoutException {
		
		logger.info("### RabbitMQ send message test ...");
		
		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		
		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		//channel.basicQos(1);
		
		// queue声明
		channel.queueDeclare(QUEUE_NAME_FANOUT1, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_FANOUT2, false, false, false, null);
		// exchange声明
		channel.exchangeDeclare(EXCHANGE_NAME_FANOUT, BuiltinExchangeType.FANOUT, false, false, null);
		channel.queueBind(QUEUE_NAME_FANOUT1, EXCHANGE_NAME_FANOUT, ROUTING_KEY_FANOUT1);
		channel.queueBind(QUEUE_NAME_FANOUT2, EXCHANGE_NAME_FANOUT, ROUTING_KEY_FANOUT2);
		
		
		// 发布消息
		String message = "Hello rabbitMQ! Exchange type is topic!";
		channel.basicPublish(EXCHANGE_NAME_FANOUT, "123.fanout.123", null, message.getBytes());
		
		// 关闭连接
		channel.close();
		connection.close();
		
	}
	
}
