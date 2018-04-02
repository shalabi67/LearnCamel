package com.learn.camel_springboot.routes;

import com.learn.camel_springboot.Facade.OrderAdapter;
import com.learn.camel_springboot.camel.routes.RestRoute;
import com.learn.camel_springboot.models.Order;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestRouteIntegrationTests.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.learn.camel_springboot.camel.routes"})
public class RestRouteIntegrationTests {

    @EndpointInject(uri = "{{bagsOrderSource}}")
    ProducerTemplate ordersQueueProducer;

    @Test
    public void shouldSucceed() throws Exception {
        ordersQueueProducer.sendBody(order.toString());
    }

    private Order order = OrderAdapter.createOrder();

}