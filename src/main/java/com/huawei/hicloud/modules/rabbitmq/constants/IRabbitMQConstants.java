package com.huawei.hicloud.modules.rabbitmq.constants;

public interface IRabbitMQConstants {
	
	/** 交换机名称 */
	public static final String EXCHANGE_DIRECT = "EXCHANGE_DIRECT";
	public static final String EXCHANGE_TOPIC = "EXCHANGE_TOPIC";
	public static final String EXCHANGE_FANOUT = "EXCHANGE_FANOUT";
	
	/* direct */
	public static final String QUEUE_DIRECT = "QUEUE_DIRECT";
	public static final String ROUTING_KEY_DIRECT = "direct.abc";
	
	/**
	 *  topic
	 *  '*'表示一个词,#表示零个或多个词
	 */
	public static final String QUEUE_TOPIC_01 = "QUEUE_TOPIC_01";
	public static final String QUEUE_TOPIC_02 = "QUEUE_TOPIC_02";
	public static final String ROUTING_KEY_TOPIC_01 = "topic.*";
	public static final String ROUTING_KEY_TOPIC_02 = "topic.message";
	
	/* fanout */
	public static final String QUEUE_FANOUT_01 = "QUEUE_FANOUT_01";
	public static final String QUEUE_FANOUT_02 = "QUEUE_FANOUT_02";
	public static final String QUEUE_FANOUT_03 = "QUEUE_FANOUT_03";
	
	
}
