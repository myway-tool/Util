package com.example.demo.utils;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

public class RabbitmqUtil {

	public static void producer() {
		try {
			// RabbitMQ 연결
			CachingConnectionFactory cf = new CachingConnectionFactory("localhost", 5672);
			cf.setUsername("guest");
			cf.setPassword("guest");
			cf.setVirtualHost("TEST01");
			
			// 메시지 보내기
			RabbitTemplate template = new RabbitTemplate(cf);
			template.setExchange("amq.direct");
			template.setQueue("myQueue");
//			template.setDefaultReceiveQueue("myQueue");
			template.convertAndSend("foo.bar", "Hello, world!");
			cf.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void consumer() {
		// RabbitMQ 연결
		CachingConnectionFactory cf = new CachingConnectionFactory("localhost", 5672);
		cf.setUsername("guest");
		cf.setPassword("guest");
		cf.setVirtualHost("TEST01");

		// 큐 생성
		RabbitAdmin admin = new RabbitAdmin(cf);
		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);

		// Exchange에 바인딩
		DirectExchange exchange = new DirectExchange("amq.direct");
		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo.bar"));

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
		Object listener = new Object() {
			// 메시지 처리
			@SuppressWarnings("unused")
			public void handleMessage(final Object foo) {
				System.out.println(foo);
			}
		};

		// 메시지 리스닝
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("myQueue");
		container.start();
	}
}
