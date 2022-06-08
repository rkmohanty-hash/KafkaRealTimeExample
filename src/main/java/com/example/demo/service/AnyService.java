package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.producer.kafka.KafkaProducerPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class AnyService {

	@Autowired
	private KafkaProducerPublisher kafkaPublisher;
	@Value("${spring.kafka.producer.bcaTermstopic}")
	private String bcaTermstopic;
	private ObjectMapper objMapper = new ObjectMapper();

	public void SendmessageToNg(String message) {
		try {
			objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			objMapper.registerModule(new JavaTimeModule());
			// calss res=objMapper.readValue(message, classType);
			kafkaPublisher.sendMessage(message, bcaTermstopic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public void consumeDataFromKafka(String message) {
		try {
			objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			objMapper.registerModule(new JavaTimeModule());
			// calss res=objMapper.readValue(message, classType);
			kafkaPublisher.sendMessage(message, bcaTermstopic);
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
