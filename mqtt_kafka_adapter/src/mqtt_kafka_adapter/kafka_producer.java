package mqtt_kafka_adapter;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * This producer will send a bunch of messages to topic "fast-messages". Every so often,
 * it will send a message to "slow-messages". This shows how messages can be sent to
 * multiple topics. On the receiving end, we will see both kinds of messages but will
 * also see how the two topics aren't really synchronized.
 */
public class kafka_producer {
     public kafka_producer(){
    	 Properties props = new Properties();
    	 props.put("bootstrap.servers", "35.176.144.206:9092");
    	 props.put("acks", "all");
    	 props.put("retries", 0);
    	 props.put("batch.size", 16384);
    	 props.put("linger.ms", 1);
    	 props.put("buffer.memory", 33554432);
    	 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	 
    	 Producer<String, String> producer = new KafkaProducer<>(props);
    	
    	 //for(int i = 0; i < 100; i++)
    		 try {
				producer.send(new ProducerRecord<String, String>("test" , "key" , "the message")).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		// producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
         System.out.println("kafka message sent");
    	 producer.close();
    }
}