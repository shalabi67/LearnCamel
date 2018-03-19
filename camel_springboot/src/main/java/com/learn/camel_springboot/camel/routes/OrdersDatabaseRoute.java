package com.learn.camel_springboot.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrdersDatabaseRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("sql:select * from orders where processed=0?" +
                "consumer.onConsume=update orders set processed=1 where id=:#id")
                .to("log:com.learn.camel_springboot.camel.routes.OrdersDatabaseRoute?level=INFO");
    }
}
