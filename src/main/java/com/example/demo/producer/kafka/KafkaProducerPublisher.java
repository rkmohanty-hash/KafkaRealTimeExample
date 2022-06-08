package com.example.demo.producer.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaProducerPublisher {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	public void sendMessage(String message,String topicName) {
		log.info("");
	ListenableFuture<SendResult<String, String>> future = null;
	try {
		future= kafkaTemplate.send(topicName, message);
	} catch (Exception e) {
		e.printStackTrace();
	}
		     
			
		    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

		        @Override
		        public void onSuccess(SendResult<String, String> result) {
		            System.out.println("Sent message=[" + message + 
		              "] with offset=[" + result.getRecordMetadata().offset() + "]");
		        }
		        @Override
		        public void onFailure(Throwable ex) {
		            System.out.println("Unable to send message=[" 
		              + message + "] due to : " + ex.getMessage());
		        }
		    });
	}
}
