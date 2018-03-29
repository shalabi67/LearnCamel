package com.learn.camel_springboot.routes;

import com.learn.camel_springboot.CamelSpringbootApplication;
import com.learn.camel_springboot.models.Order;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RestRouteTests.class})
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan //(basePackages = {"com.learn.camel_testing.camel.routes"})
public class RestRouteTests {

    @EndpointInject(uri = "{{bagsOrderSource}}")
    ProducerTemplate ordersQueueProducer;

    @EndpointInject(uri = "{{restEndPoint}}")
    MockEndpoint restMock;


    @Test
    @DirtiesContext
    public void shouldSucceed() throws Exception {
        Assert.assertNotNull(restMock);
        Assert.assertNotNull(ordersQueueProducer);
        int messageCount = 1;

        //MockEndpoint restMock = getMockEndpoint(restEndPoint);
        restMock.expectedMessageCount(messageCount);
        restMock.expectedBodiesReceived(order.toString());

        ordersQueueProducer.sendBody(order.toString());

        restMock.assertIsNotSatisfied();
        List<Exchange> list = restMock.getReceivedExchanges();
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