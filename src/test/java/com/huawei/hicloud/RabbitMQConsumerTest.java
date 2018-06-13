package com.huawei.hicloud;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMQConsumerTest {
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

	// ExchangeTypes

	@Test
	public void recvTest() throws Exception {

		logger.info("### RabbitMQ recv message test ...");

		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		// channel.basicQos(1);

		channel.queueDeclare(QUEUE_NAME_DEFAULT, false, false, false, null);

		// 发布消息
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// super.handleDelivery(consumerTag, envelope, properties, body);

				logger.info(
						"### handleDelivery: consumerTag: " + consumerTag + ", envelope: " + JSON.toJSONString(envelope)
								+ ", properties: " + JSON.toJSONString(properties) + ", body: " + new String(body));

				channel.basicAck(envelope.getDeliveryTag(), false);
			}

		};
		channel.basicConsume(QUEUE_NAME_DEFAULT, false, consumer);

		int delay = 20;
		while (true) {
			if (delay > 0) {
				logger.info("### delay 5000ms " + delay + " times!");
				delay--;
				Thread.sleep(5000);
			} else {
				break;
			}
		}

		// 关闭连接
		channel.close();
		connection.close();

	}

	/**
	 * direct
	 * @throws Exception
	 */
	@Test
	public void directRecvTest() throws Exception {

		logger.info("### RabbitMQ direct recv message test ...");

		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		// channel.basicQos(1);

		channel.queueDeclare(QUEUE_NAME_DIRECT, false, false, false, null);
		channel.exchangeDeclare(EXCHANGE_NAME_DIRECT, BuiltinExchangeType.DIRECT, false, false, null);
		channel.queueBind(QUEUE_NAME_DIRECT, EXCHANGE_NAME_DIRECT, ROUTING_KEY_DIRECT);

		// 发布消息
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// super.handleDelivery(consumerTag, envelope, properties, body);

				logger.info(
						"### handleDelivery: consumerTag: " + consumerTag + ", envelope: " + JSON.toJSONString(envelope)
								+ ", properties: " + JSON.toJSONString(properties) + ", body: " + new String(body));

				channel.basicAck(envelope.getDeliveryTag(), false);
			}

		};
		channel.basicConsume(QUEUE_NAME_DIRECT, false, consumer);

		int delay = 20;
		while (true) {
			if (delay > 0) {
				logger.info("### delay 5000ms " + delay + " times!");
				delay--;
				Thread.sleep(5000);
			} else {
				break;
			}
		}

		// 关闭连接
		channel.close();
		connection.close();

	}

	/**
	 * topic
	 * @throws Exception
	 */
	@Test
	public void topicRecvTest() throws Exception {

		logger.info("### RabbitMQ topic recv message test ...");

		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		// channel.basicQos(1);

		// queue声明
		channel.queueDeclare(QUEUE_NAME_TOPIC1, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_TOPIC2, false, false, false, null);
		// exchange声明
		channel.exchangeDeclare(EXCHANGE_NAME_TOPIC, BuiltinExchangeType.TOPIC, false, false, null);
		channel.queueBind(QUEUE_NAME_TOPIC1, EXCHANGE_NAME_TOPIC, ROUTING_KEY_TOPIC1);
		channel.queueBind(QUEUE_NAME_TOPIC2, EXCHANGE_NAME_TOPIC, ROUTING_KEY_TOPIC2);

		// 消费消息
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// super.handleDelivery(consumerTag, envelope, properties, body);

				logger.info(
						"### handleDelivery: consumerTag: " + consumerTag + ", envelope: " + JSON.toJSONString(envelope)
								+ ", properties: " + JSON.toJSONString(properties) + ", body: " + new String(body));

				channel.basicAck(envelope.getDeliveryTag(), false);
			}

		};
		channel.basicConsume(QUEUE_NAME_TOPIC1, false, consumer);

		int delay = 20;
		while (true) {
			if (delay > 0) {
				logger.info("### delay 5000ms " + delay + " times!");
				delay--;
				Thread.sleep(5000);
			} else {
				break;
			}
		}

		// 关闭连接
		channel.close();
		connection.close();

	}

	/**
	 * fanout
	 * 
	 * @throws Exception
	 */
	@Test
	public void fanoutRecvTest() throws Exception {

		logger.info("### RabbitMQ fanout recv message test ...");

		// 设置connnection factory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");

		// 获取连接
		Connection connection = connectionFactory.newConnection();
		// 获取通道
		Channel channel = connection.createChannel();
		// channel.basicQos(1);

		// queue声明
		channel.queueDeclare(QUEUE_NAME_FANOUT1, false, false, false, null);
		channel.queueDeclare(QUEUE_NAME_FANOUT2, false, false, false, null);
		// exchange声明
		channel.exchangeDeclare(EXCHANGE_NAME_FANOUT, BuiltinExchangeType.FANOUT, false, false, null);
		channel.queueBind(QUEUE_NAME_FANOUT1, EXCHANGE_NAME_FANOUT, ROUTING_KEY_FANOUT1);
		channel.queueBind(QUEUE_NAME_FANOUT2, EXCHANGE_NAME_FANOUT, ROUTING_KEY_FANOUT2);

		// 消费消息
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// super.handleDelivery(consumerTag, envelope, properties, body);

				logger.info(
						"### handleDelivery: consumerTag: " + consumerTag + ", envelope: " + JSON.toJSONString(envelope)
								+ ", properties: " + JSON.toJSONString(properties) + ", body: " + new String(body));

				channel.basicAck(envelope.getDeliveryTag(), false);
			}

		};
		channel.basicConsume(QUEUE_NAME_FANOUT1, false, consumer);
		channel.basicConsume(QUEUE_NAME_FANOUT2, false, consumer);

		int delay = 20;
		while (true) {
			if (delay > 0) {
				logger.info("### delay 5000ms " + delay + " times!");
				delay--;
				Thread.sleep(5000);
			} else {
				break;
			}
		}

		// 关闭连接
		channel.close();
		connection.close();

	}

}
