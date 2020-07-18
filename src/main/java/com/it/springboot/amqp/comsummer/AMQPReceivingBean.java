package com.it.springboot.amqp.comsummer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AMQPReceivingBean {

	@RabbitListener(queues = "topic.man")
	public void processMessage(Map content) {
		// ...
		System.out.println("TopicManReceiver消费者收到消息  : " + content.toString());

	}

}