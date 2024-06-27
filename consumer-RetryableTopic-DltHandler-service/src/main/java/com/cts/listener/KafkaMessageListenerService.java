package com.cts.listener;

import com.cts.dto.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class KafkaMessageListenerService {

	//@RetryableTopic(attempts = "5", backoff = @Backoff(delay = 3000, multiplier = 1.5, maxDelay = 1500))
	//@RetryableTopic(attempts = "5", exclude = {NullPointerException.class, ClassNotFoundException.class})
	@RetryableTopic(attempts = "5")
	@KafkaListener(topics = "employee-topic12",groupId = "employee_consume12")
	public void consumeEmployeeMessage(String emp, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
									   @Header(KafkaHeaders.OFFSET) long offset) {
		System.out.println("consumeEmployeeMessage-1 data "+ emp);
		if(emp.contains("QWERTY"))
			throw new RuntimeException("Invalid ID Address received !");
	}

	@DltHandler
	public void listenDLT(String emp, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
						  @Header(KafkaHeaders.OFFSET) long offset) {
		log.info("DLT Received : {} , Topic {} , offset {}",emp,topic,offset);
	}}
