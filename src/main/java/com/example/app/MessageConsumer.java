package com.example.app;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
	
	@KafkaListener(topics = "hello")
	public void helloListener(String message) {
		System.out.println(message);
	}

}
