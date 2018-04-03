package com.learn.camel_springboot.Facade;

import com.learn.camel_springboot.models.Order;

public class OrderAdapter {
    public static Order createOrder() {
        Order order = new Order();
        order.setType(1);
        order.setProcessed(false);
        order.setDescription("testing order");
        order.setId(100);
        order.setAmount(1000.0);

        return order;
    }
}
