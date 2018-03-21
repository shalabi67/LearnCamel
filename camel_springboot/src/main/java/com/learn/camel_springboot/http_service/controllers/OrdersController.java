package com.learn.camel_springboot.http_service.controllers;

import com.learn.camel_springboot.camel.translator.OrderTranslator;
import com.learn.camel_springboot.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController {
    private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        LOG.info(order.toString());

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listOrders() {
        LOG.debug("list orders");
        List<Order> orders = new ArrayList<>();

        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
}
