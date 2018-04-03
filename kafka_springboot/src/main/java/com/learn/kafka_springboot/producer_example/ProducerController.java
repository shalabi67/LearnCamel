package com.learn.kafka_springboot.producer_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    Producer producer;
    @RequestMapping(value ="/producer", method = RequestMethod.GET)
    public void sendMessage() {
        producer.send("hello");
    }
}
