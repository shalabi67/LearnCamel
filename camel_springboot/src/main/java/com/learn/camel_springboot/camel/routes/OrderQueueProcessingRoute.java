package com.learn.camel_springboot.camel.routes;

import com.learn.camel_springboot.camel.translator.OrderTranslator;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderQueueProcessingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:ORDERS")
                .bean(OrderTranslator.class, "toJson")
                .to("log:com.learn.camel_springboot.camel.routes.OrdersDatabaseRoute?level=INFO");
    }
}
