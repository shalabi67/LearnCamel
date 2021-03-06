package com.learn.camel_springboot.routes;

import com.learn.camel_springboot.camel.routes.FileSelectionRoute;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
//this code is working
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FileSelectionRouteTests.class)
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.learn.camel_springboot.camel.routes"})
*/
@ActiveProfiles("test")
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FileSelectionRouteTests {

    public static final String NICE_DOG = "nice dog", NASTY_CAT="nasty cat", SUPERNASTY_CAT="super nasty cat";

    @EndpointInject(uri = "{{dogEndpoint}}")
    protected MockEndpoint dogEndpoint;

    @EndpointInject(uri = "{{catEndpoint}}")
    protected MockEndpoint catEndpoint;


    @EndpointInject(uri = "{{animalSource}}")
    protected ProducerTemplate animalSource;

    @Test
    @DirtiesContext
    public void testDog() throws Exception {

        animalSource.sendBodyAndHeader("test", FileSelectionRoute.CAMEL_FILE_NAME, NICE_DOG);


        dogEndpoint.message(0).predicate(m -> {
            String header = m.getIn().getHeader(FileSelectionRoute.CAMEL_FILE_NAME).toString();
            return NICE_DOG.equals(header);
        });
        dogEndpoint.expectedMessageCount(1);
        dogEndpoint.assertIsSatisfied();

        catEndpoint.expectedMessageCount(0);
        catEndpoint.assertIsSatisfied();
    }

    @Test
    @DirtiesContext
    public void testCat() throws Exception {


        animalSource.sendBodyAndHeader("test", FileSelectionRoute.CAMEL_FILE_NAME, NASTY_CAT);
        animalSource.sendBodyAndHeader("test", FileSelectionRoute.CAMEL_FILE_NAME, SUPERNASTY_CAT);


        catEndpoint.message(0).predicate(m -> {
            String header = m.getIn().getHeader(FileSelectionRoute.CAMEL_FILE_NAME).toString();
            return NASTY_CAT.equals(header);
        });

        catEndpoint.message(1).predicate(m -> {
            String header = m.getIn().getHeader(FileSelectionRoute.CAMEL_FILE_NAME).toString();
            return SUPERNASTY_CAT.equals(header);
        });

        catEndpoint.expectedMessageCount(2);

        dogEndpoint.expectedMessageCount(0);

        dogEndpoint.assertIsSatisfied();

        catEndpoint.assertIsSatisfied();



    }

}