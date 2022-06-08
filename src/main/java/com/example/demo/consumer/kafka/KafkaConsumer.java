package com.example.demo.consumer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.service.AnyService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {
	@Autowired
	private AnyService service;
	
	@KafkaListener(topics = "${spring.kafka.producer.destination-topic}", groupId = "${spring.kafka.producer.group-id}")
	public void listenGroupFoo(String message) {
	    System.out.println("Received Message in group foo: " + message);
	    service.consumeDataFromKafka(message);
	}
	

}
