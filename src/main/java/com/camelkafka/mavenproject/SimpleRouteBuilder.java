package com.camelkafka.mavenproject;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder  {

	@Override
	public void configure() throws Exception {
		// kafkaServerAddress to be replaced with the Kafka Server ip address
		String kafkaServerAddress = "3.106.231.226";
		String topicName = "topic=camel-kafka-topic";
		String kafkaServer = "kafka:" + kafkaServerAddress + ":9092";
		String zooKeeperHost = "zookeeperHost=" + kafkaServerAddress + "&zookeeperPort=2181";
		String serializerClass = "serializerClass=kafka.serializer.StringEncoder";

		String toKafka = new StringBuilder().append(kafkaServer).append("?").append(topicName).append("&")
				.append(zooKeeperHost).append("&").append(serializerClass).toString();

		from("file:C:/inbox?noop=true").split().tokenize("\n").to(toKafka);
	}

}
