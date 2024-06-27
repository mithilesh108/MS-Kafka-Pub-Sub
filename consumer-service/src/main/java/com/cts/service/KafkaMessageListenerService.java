package com.cts.service;

import com.cts.dto.EmployeeDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListenerService {

//	@KafkaListener(topics = "student-topic",groupId = "student_consumer")
//	public void firstStudentConsumer(String message) {
//		System.out.println("firstStudentConsumer-1 data "+ message);
//	}
//	@KafkaListener(topics = "student-topic",groupId = "student_consumer")
//	public void secondStudentConsumer(String message) {
//		System.out.println("secondStudentConsumer-2 data "+ message);
//	}
//	@KafkaListener(topics = "student-topic",groupId = "student_consumer")
//	public void thirdStudentConsumer(String message) {
//		System.out.println("thirdStudentConsumer-3 data "+ message);
//	}

	//employee_consumer7: partitions assigned: [employee-topic7-0, employee-topic7-1, employee-topic7-2]
	@KafkaListener(topics = "employee-topic7",groupId = "employee_consumer7")
	public void consumeEmployeeMessage(EmployeeDTO message) {
		System.out.println("consumeEmployeeMessage-1 data "+ message);
	}
   //using below cnfgs we can consume data from specific partitions of Topic
	//[Consumer clientId=consumer-employee_consumer10-2, groupId=employee_consumer10]
	@KafkaListener(topics = "employee-topic10", groupId = "employee_consumer10", topicPartitions = {
			@TopicPartition(topic = "employee-topic10", partitions = { "2" }) })
	public void consumeEvents3(EmployeeDTO message) {
		System.out.println("consumeEvents-3 data "+ message);
	}
}
