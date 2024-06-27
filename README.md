vent Driven Architecture : Event driven architecture is a software design model build around the publication, capture, processing and storing of events. So basically Event driven uses event to trigger and communicate between decoupled services. This is common in modern application development with microservice.
Apache Kafka : Kafka is open-source, Message streaming platform, that uses publish and subscribe mechanism to stream the records. Here topic will be use to store the message which sent by the publisher and then subscriber that subscribe to that topic and will consume that 
message. Here multiple subscribers can subscribe one topic and message remain persistent in topic until we delete the topic.
Usecase of Kafka : Its very useful in MS architecture if one ms want to send some data to another ms and if another ms is not available in this case we lose the first ms data hear, Kafka messaging system come into the picture. Basically Kafka store the ms message into topic and same topic can access by another ms when its available. Kafka works on pub/sub model. 
How Kafka tackle fault tolerance : In a Kafka cluster, each server is called a broker. Brokers store data and serve client requests. Kafka's fault tolerance is primarily achieved through replication. Each topic in Kafka can be configured with a replication factor, which defines the number of copies of the data that will be kept across different brokers
Kafka Components  :
	• Producer : Producer will Publish the event which will take by broker
	• Consumer : Consumer will consume the events or messages from broker
	• Broker  : Kafka plays as Broker role or just a kafka server. or we can say its an immediater that helps in messages exchange between producer and consumer.
	• Cluster  : Cluster is a common terminology in distributed system. Groups of kafka server is knows as kafka cluster
	• Topic    : A kafka server can have one or more topic based on category which is getting used to store message.
	• Partitions : A topic can have one or more partitions which improve the performance while store msg or events if producer produce huge data. Here one Partition will talk with one Consumer. If one consumer goes down then another consume will get to change to read message where previous consume left with the help of Offset. 
	• Replication factor : Apache Kafka ensures high data availability by replicating data via the replication factor in Kafka. The replication factor is the number of nodes to which your data is replicated. When a producer writes data to Kafka, it sends it to the broker designated as the 'Leader' for that topic:partition in the cluster. Such a broker is the entry point to the cluster for the topic's data:partition. If we use 'replication factor' >1, writes will also propagate to other brokers called 'followers.' This fundamental operation enables Kafka to provide high availability (HA).
	• Offset  : when message come  it can store any partitions of a topic which is not in our control and all msg get store in partition as similar to array format so here array's index, can say offset in partitions. Just assume if a consumer consume the message and due to some error its goes down. In this case when its up and back to start consuming the message, Offset value basically help the consumer to access the message where he left due to downtime. 
	• Consumer Groups : making replica of consumer is knows as Consumer group, so using that we can divide the work load to consume message from different different partition. Using this technique we can get better performance or better response. 
Zookeeper : It is key component of Kafak. uses for tracking the status of Kafka cluster node. it also keep tracks of Kafka topics, Partitions, Offset etc. ![Uploading image.png…]()


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
