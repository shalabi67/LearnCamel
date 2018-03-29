package com.learn.camel_springboot.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * read messages from BAGS_ORDERS queue and post them to http://localhost:7777/ http service.
 */
@Component
public class RestRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("{{bagsOrderSource}}")
                .log("send to rest service")
                .setHeader("CamelHttpMethod", constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("{{restEndPoint}}");
    }
}
