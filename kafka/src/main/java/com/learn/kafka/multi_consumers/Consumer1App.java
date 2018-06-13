package com.learn.kafka.multi_consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer1App {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", Constants.ADDRESS);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", Constants.GROUP_ID);

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList(Constants.TOPIC));

        try{
            while(true) {
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);

                for(ConsumerRecord<String, String> record : consumerRecords) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key:%s, Value: %s",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                }

            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            //kafkaConsumer.unsubscribe();
            kafkaConsumer.close();
        }


    }
}
