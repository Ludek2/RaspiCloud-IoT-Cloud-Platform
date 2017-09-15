package microservice;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
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
       consumerConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
       //consumerConfig.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
       KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig);
       TestConsumerRebalanceListener rebalanceListener = new TestConsumerRebalanceListener();
       AfterCommit afterCommit = new AfterCommit();
       consumer.subscribe(Collections.singletonList(topic));
       //System.out.println("metrics here:");
       //System.out.println(consumer.metrics());

	       while (true) {
	           System.out.println("poll");
	    	       ConsumerRecords<String, String> records = consumer.poll(1000);
	           for (ConsumerRecord<String, String> record : records) {
	               System.out.printf("Received Message topic =%s, partition =%s, offset = %d, key = %s, value = %s\n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
	               String msg=record.value();
	               eh.msg_received(msg);
	               try {
	            	   	Thread.sleep(10);
	               } catch (InterruptedException e) {
	            	   	// TODO Auto-generated catch block
	            	   	e.printStackTrace();
	               }
	           }
	           System.out.println("-------- commited now ---------");
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
	  
	  private static class AfterCommit implements OffsetCommitCallback {
		
		public void onComplete(Map<TopicPartition, OffsetAndMetadata> arg0, Exception arg1) {
			System.out.println("commited..............");
			
		}
	  }
}
