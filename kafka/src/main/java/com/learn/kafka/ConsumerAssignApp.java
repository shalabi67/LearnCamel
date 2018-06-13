package com.learn.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConsumerAssignApp {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //properties.put("bootstrap.servers", ":9095,ubuntu:9096");
        properties.put("bootstrap.servers", "192.168.1.203:9098");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "consumer_example");

        TopicPartition topicPartition = new TopicPartition("consumer_topic", 0);
        List<TopicPartition> partitions = new ArrayList<>();
        partitions.add(topicPartition);

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.assign(partitions);

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
