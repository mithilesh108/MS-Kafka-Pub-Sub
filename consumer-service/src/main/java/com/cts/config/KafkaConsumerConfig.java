package com.cts.config;

import java.util.HashMap;
import java.util.Map;

import com.cts.dto.EmployeeDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,	StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		//props.put(JsonDeserializer.TRUSTED_PACKAGES, "*"); // not working
		//as per my rnd in producer dto(com.cts.dto.sub.EmployeeDTO) package should match with consumer dto(com.cts.dto.sub.EmployeeDTO) package
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.cts.dto");
		return props;
	}

	@Bean
	public ConsumerFactory<String, EmployeeDTO> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EmployeeDTO>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EmployeeDTO> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}