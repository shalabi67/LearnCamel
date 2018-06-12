package com.learn.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerApp {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //properties.put("bootstrap.servers", ":9095,ubuntu:9096");
        properties.put("bootstrap.servers", "192.168.1.203:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        try {
            for(int i=0;i<1000;i++) {
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("single_topic", "Key" + i, "hello from java message " + i);
                kafkaProducer.send(producerRecord);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }


    }
}
