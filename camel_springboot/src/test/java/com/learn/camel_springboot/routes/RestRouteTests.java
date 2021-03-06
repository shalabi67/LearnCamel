package com.learn.camel_springboot.routes;

import com.learn.camel_springboot.Facade.OrderAdapter;
import com.learn.camel_springboot.camel.routes.RestRoute;
import com.learn.camel_springboot.models.Order;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestRouteTests.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.learn.camel_springboot.camel.routes"})
public class RestRouteTests {

    @EndpointInject(uri = "{{bagsOrderSource}}")
    ProducerTemplate ordersQueueProducer;

    @EndpointInject(uri = "{{restEndPoint}}")
    MockEndpoint restMock;


    @Test
    public void testRoute() throws Exception {
        Assert.assertNotNull(restMock);
        Assert.assertNotNull(ordersQueueProducer);
        int messageCount = 1;

        restMock.expectedMessageCount(messageCount);
        restMock.expectedBodiesReceived(order.toString());

        ordersQueueProducer.sendBody(order.toString());
        List<Exchange> list = restMock.getReceivedExchanges();

        Assert.assertEquals("Expected one exchange", messageCount, list.size());
        Map<String, Object> header =  list.get(0).getIn().getHeaders();
        Assert.assertEquals("expected POST method", HttpMethods.POST.name(), header.get(Exchange.HTTP_METHOD));
        Assert.assertEquals("expected json content type", RestRoute.JSON_APPLICATION, header.get(Exchange.CONTENT_TYPE));

        restMock.assertIsSatisfied();
    }

    private Order order = OrderAdapter.createOrder();
}