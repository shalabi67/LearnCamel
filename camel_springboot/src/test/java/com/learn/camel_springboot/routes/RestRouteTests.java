package com.learn.camel_springboot.routes;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


//@RunWith(CamelSpringJunit4ClassRunner.class)
@ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest

public class RestRouteTests extends CamelTestSupport {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private RestRoute restRoute;


    @Override
    protected RouteBuilder createRouteBuilder() {
        return restRoute;
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return camelContext;
    }
 
    @EndpointInject(uri = "activemq:BAGS_ORDERS")
    private ProducerTemplate ordersQueueProducer;

    @Value("${restEndPoint}")
    private String restEndPoint;
 
    @Override
    public void setUp() throws Exception {
        super.setUp();
        //RouteDefinition definition = context().getRouteDefinitions().get(0);
        //definition.adviceWith(context(), restRoute);
    }
 
    @Override
    public String isMockEndpointsAndSkip() {
            return "myEndpoint:put*";
    }
 
    @Test
    public void shouldSucceed() throws Exception {
        assertNotNull(camelContext);
        assertNotNull(ordersQueueProducer);
        int messageCount = 1;

        MockEndpoint restMock = getMockEndpoint(restEndPoint);
        restMock.expectedMessageCount(messageCount);
        restMock.expectedBodiesReceived(order.toString());

        ordersQueueProducer.sendBody(order.toString());

        restMock.assertIsNotSatisfied();
        //List<Exchange> list = restMock.getReceivedExchanges();
        //Assert.assertEquals(messageCount, list.size());

        /*
        String expectedValue = "expectedValue";
        MockEndpoint mock = getMockEndpoint("mock:myEndpoint:put");
        mock.expectedMessageCount(1);
        mock.allMessages().body().isEqualTo(expectedValue);
        mock.allMessages().header(MY_HEADER).isEqualTo("testHeader");
        endpoint.sendBodyAndHeader("test", MY_HEADER, "testHeader");
         
        mock.assertIsSatisfied();
        */
    }

    private Order order = createOrder();

    private Order createOrder() {
        Order order = new Order();
        order.setType(1);
        order.setProcessed(false);
        order.setDescription("testing order");
        order.setId(100);
        order.setAmount(1000.0);

        return order;
    }
}