package com.learn.camel_springboot.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * reads from JSON_ORDERS and write to other queues based on Order.type.
 */
@Component
public class OrderContetTypeRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:JSON_ORDERS")
                .choice()
                    .when().jsonpath("$[?(@.type == 1)]")
                        .to("activemq:CLOTHES_ORDERS")
                    .when().jsonpath("$[?(@.type == 2)]")
                        .to("activemq:BAGS_ORDERS")
                    .when().jsonpath("$[?(@.type == 3)]")
                        .to("activemq:BIRDS_ORDERS")
                    .otherwise()
                        .to("activemq:ERROR_ORDERS");
    }
}
