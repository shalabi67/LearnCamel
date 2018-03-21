package com.learn.camel_springboot.camel.translator;

import com.learn.camel_springboot.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderTranslator {
    private static final Logger LOG = LoggerFactory.getLogger(OrderTranslator.class);
    public String toJson(Order order) {
        LOG.info("tojson started.");
        if(order == null)
            return "";

        /*
        StringBuilder orderBuilder = new StringBuilder("{");
        for(String key : orderMap.keySet()) {
            orderBuilder.append("'" + key + "':'" + orderMap.get(key) + "',");
        }
        orderBuilder.append("}");
        String orderString = orderBuilder.toString();
        orderString = orderString.replace(",}", "}");

        LOG.info(orderString);
        */

        //Order order = getOrder(orderMap);


        LOG.info(order.toString());
        return order.toString();
    }

    private Order getOrder(Map<String, Object> orderMap) {
        Order order = new Order();
        order.setId((int)getValue(orderMap, "ID"));
        order.setDescription((String)getValue(orderMap, "DESCRIPTION"));
        order.setAmount((Double)getValue(orderMap, "AMOUNT"));
        order.setProcessed((Boolean)getValue(orderMap, "PROCESSED"));
        order.setType((int)getValue(orderMap, "TYPE"));
        return order;
    }

    public Order toOrder(Map<String, Object> orderMap) {
        Order order = getOrder(orderMap);
        return order;
    }
    private Object getValue(Map<String, Object> orderMap, String key) {
        if(orderMap.containsKey(key)) {
            return orderMap.get(key);
        }

        return null;
    }
}
