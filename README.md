# java-client-camel-kafka
Project is created using Eclipse IDE + Maven
<h1>Add the following dependencies in your pom.xml file</h1>
<pre>
  
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-core</artifactId>
        <version>2.13.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-kafka</artifactId>
        <version>2.16.3</version>
    </dependency>
    
    <dependency>
        <groupId>org.scala-lang</groupId>
        <artifactId>scala-library</artifactId>
        <version>2.11.0</version>
    </dependency>
    
</pre>

<h1> Define the route. </h1>
<pre>
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
</pre>

<h1>Load the Routes</h1>

<pre>  
package com.camelkafka.mavenproject;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class App {
    public static void main(String[] args) {
        SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();
        try {
            ctx.addRoutes(routeBuilder);
            ctx.start();
            Thread.sleep(5 * 60 * 1000);
            ctx.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
      System.out.println("Hello World!");
    }
}
</pre> <br/>
Note: <u>Done with the java code.</u> <br/>
<br/>
<h1>Apache Zookeeper and Kafka</h1>
<ul>
  <li>Start Apache Zookeeper <br/>
  <pre>
    ubuntu@ip-172-31-1-82:~/kafka_2.12-3.5.1$ zookeeper-server-start.sh config/zookeeper.properties
  </pre>
    ![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/8e80d999-54cc-4aa5-8be0-360e33dc9de6)

  </li>
  <li>Start Kafka Server 
  <br/>
    <pre>
      ubuntu@ip-172-31-1-82:~/kafka_2.12-3.5.1$ kafka-server-start.sh config/server.properties
    </pre>
    ![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/e07d8311-a38b-4b5d-a3ee-e59abd143aa8)
  </li>
  <li>Create a topic with any name. E.g "camel-kafka-topic" that has only one partition &<br/>
  <pre>
ubuntu@ip-172-31-1-82$ kafka-topics.sh --create --topic camel-kafka-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
Created topic camel-kafka-topic.

ubuntu@ip-172-31-1-82:$ kafka-topics.sh --list --bootstrap-server localhost:9092
camel-kafka-topic
  </pre>
  </li>
  <li>Start consumer listening to "camel-kafka-topic"<br/>
  <pre>
    ubuntu@ip-172-31-1-82$ kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic camel-kafka-topic --from-beginning
  </pre> <br/>
  ![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/99156771-fe69-4739-848b-085900101896)
  </li>
  <li>In C:/inbox folder have a file with the following content <br/>
  <pre>
    Learn
    Java
    Camel
    Kafka
    Tutorial
    Good luck!
  </pre>
  ![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/c86ab6f5-6870-407d-9b36-8aa30b94cae3)

  </li>
</ul>
<h1>How to run Java client code?</h1>
<ul>
  <li>STEP 1. Open Eclipse IDE, create a workspace</li>
  <li>STEP 2. Import Project (Maven) Existing Projects <br/>
  ![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/6dc8b6ee-98d2-4414-be40-245f9685a25e)
  <br/>
  ![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/07c64330-c749-4960-a8b1-684281b6d88e)
  </li>
  <li>
  STEP 3. Run App.java and Replace the kafkaServerAddress
  </li>
</ul>
<h1>Output after running Java Client and Kafka Server</h1>
<br/>
![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/4c61ff3c-c8ee-4c89-9c84-25c110169d81)
<br/>
![image](https://github.com/jsusanto/java-client-camel-kafka/assets/1523220/e73fa8b9-c602-4622-bf82-eb78cb6d6d76)

<br/>
<h1>Reference:</h1>
https://www.javainuse.com/camel/camel-kafka-example
