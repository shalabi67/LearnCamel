package com.learn.camel_springboot.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileSelectionRoute extends RouteBuilder {
    public static final String CAMEL_FILE_NAME = "CamelFileName";

    @Value("${animalSource}")
    private String animalSourceFolder;


    @Override
    public void configure() throws Exception {
        from(animalSourceFolder)
                .log("got message")
                .choice()
                .when(p -> p.getIn().getHeader(CAMEL_FILE_NAME).toString().contains("dog"))
                .log("found a dog!")
                .to("{{dogEndpoint}}")
                .when(p -> p.getIn().getHeader(CAMEL_FILE_NAME).toString().contains("cat"))
                .log("looks like a cat!")
                .to("{{catEndpoint}}")
                .otherwise()
                .log("no cat or dog found")
                .to("{{otherEndpoint}}");


    }
}
