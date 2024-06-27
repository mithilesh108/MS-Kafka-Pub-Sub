package com.cts.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

	@Bean
	public NewTopic createTopic() {
		return new NewTopic("employee-topic12", 3, (short) 1); // 5-partitions, 1-replication-factor
		//return new NewTopic("employee-topic", 5, (short) 1); // 5-partitions, 1-replication-factor
		//return new NewTopic("employee-topic", 5, (short) 3); // 5-partitions, 3-replication-factor, need to run 3 kafka broker instance
	}

	@Bean
	public Map<String, Object> producerConfigDetails() {
		Map<String, Object> props = new HashMap<>();
		//we can add multiple kafka broker and can see on "Offset Explorer" tool
		//props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092 , localhost:9093");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		// Defines the class used to serialize the key of the message being sent.
		// meaning the keys are serialized as strings.
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		// Defines the class used to serialize the value of the message being sent.
		// meaning the values are serialized as json.
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.cts.dto");
		return props;
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		return new DefaultKafkaProducerFactory<String, Object>(producerConfigDetails());
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
