package com.learn.camel_springboot.configuration;

import com.learn.camel_springboot.camel.routes.RestRoute;
import com.learn.camel_springboot.routes.RestRouteTests;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.learn.camel_springboot"})
public class RoutesConfiguration {

    @Bean
    public RestRoute restRoute() {
        return new RestRoute();
    }
}
