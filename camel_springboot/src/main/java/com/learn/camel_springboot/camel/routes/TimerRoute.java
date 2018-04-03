package com.learn.camel_springboot.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("{{timer-route.start}}")
                .routeId("TimerRoute")
                .log("Timer fired")
                .to("log:aaa?")
                .to("{{timer-route.log-endPoint}}");
    }
}
