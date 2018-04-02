package com.learn.camel_springboot.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * read messages from BAGS_ORDERS queue and post them to http://localhost:7777/ http service.
 */
@Component
public class RestRoute extends RouteBuilder  {
    public static final String JSON_APPLICATION = "application/json";
    public static final String REST_ROUT_ID = "REST_ROUT";

    @Override
    public void configure() throws Exception {
        from("{{bagsOrderSource}}")
                .routeId(REST_ROUT_ID)
                .log("send to rest service")
                .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
                .setHeader(Exchange.CONTENT_TYPE, constant(JSON_APPLICATION))
                .to("{{restEndPoint}}");
    }
}
