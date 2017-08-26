package microservice;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class Kafka_consumer {
	
	Event_handler eh;
	String topic;
	
	public Kafka_consumer(String topic, Event_handler eh) {
	   this.topic = topic;
	   this.eh=eh;
		   
	   Properties consumerConfig = new Properties();
       consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "35.176.144.206:9092");
       consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
       consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
       consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
       consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
      
       KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig);
       TestConsumerRebalanceListener rebalanceListener = new TestConsumerRebalanceListener();
       consumer.subscribe(Collections.singletonList(topic));

	       while (true) {
	           ConsumerRecords<String, String> records = consumer.poll(1000);
	           for (ConsumerRecord<String, String> record : records) {
	               System.out.printf("Received Message topic =%s, partition =%s, offset = %d, key = %s, value = %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
	               String msg=record.value();
	               eh.msg_received(msg);
	           }
	           consumer.commitSync();
	       }
	   }
	
	  private static class  TestConsumerRebalanceListener implements ConsumerRebalanceListener {
           @Override
           public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
               System.out.println("Called onPartitionsRevoked with partitions:" + partitions);
           }

           @Override
           public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
               System.out.println("Called onPartitionsAssigned with partitions:" + partitions);
           }
    	   }
}
