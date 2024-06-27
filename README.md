To work with Apache Kafka local windows environment, follow below steps
	• Download Kafka from https://kafka.apache.org/downloads portal as tgz extension file and extract in one folder and placed in direct C drive 
	• Open C:\kafka\config\server.properties file and update #log.dirs=/tmp/kafka-logs to log.dirs=c:/kafka/kafka-logs
	• Open C:\kafka\config\zookeeper.properties file and update #dataDir=/tmp/zookeeper to dataDir=c:/kafka/zookeeper_data
	• Open cmd prompt in Kafka dire and hit .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties to run zookeeper, default port 2181
	• Open another cmd prompt in Kafka dire and hit .\bin\windows\kafka-server-start.bat .\config\server.properties to run Kafka-server, default port 9092
	• Open another cmd prompt and create topic -> kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --topic test
	• Produce some data using kafka-console-producer.bat --broker-list localhost:9092 --topic test
		{"Name: "John", "Age":"31", "Gender":"Male"} 
		{"Name: "Emma", "Age":"27", "Gender":"Female"}
		 {"Name: "Ronald", "Age":"17", "Gender":"Male"}
	• Open another cmd prompt to consume the topic using kafka-console-consumer.bat --topic test --bootstrap-server localhost:9092 --from-beginning
	• 
	
		} create topic : bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic javatechie-topic --partitions 3 --replication-factor 1 
		} To see all topic in Linux         :  bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
		} To see all topic in Windows :  C:\kafka\bin\windows>kafka-topics.bat --bootstrap-server localhost:9092 --list
		} To describe a topic : bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic javatechie-topic
	•  Offset Explorer tool : here we can find all created topics and partitions as UI. And also we can see lag column status to verify consumer consume all publish message or not.
	• Run producer via terminal : bin/kafka-console-producer.sh --broker-list localhost:9092 --topic javatechie-topic  (send some msg hi hello)
		}     send huge data as csv file : bin/kafka-console-producer.sh --broker-list localhost:9092 --topic javatechie-topic </users/download/users.csv
	• Run Consumer : bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic javatechie-topic --from--beginning (read some msg hi hello)

• Conclusion: Overall, integrating Kafka into our Spring Boot application enabled us to build a highly scalable, fault-tolerant messaging system capable of handling real-time data streams and supporting complex event-driven architectures."
![image](https://github.com/mithilesh108/MS-Kafka-Pub-Sub/assets/62215905/0f0981b0-728f-4edc-beef-485e326a421d)
