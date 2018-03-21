package com.learn.camel_springboot.camel.routes;

import com.learn.camel_springboot.camel.translator.OrderTranslator;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * reads from ORDERS queue convert to json object and write to JSON_ORDERS queue.
 */
@Component
public class OrderQueueProcessingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:ORDERS")
                .bean(OrderTranslator.class, "toJson")
                //.to("log:com.learn.camel_springboot.camel.routes.OrdersDatabaseRoute?level=INFO");
                .to("activemq:JSON_ORDERS");
    }
}
