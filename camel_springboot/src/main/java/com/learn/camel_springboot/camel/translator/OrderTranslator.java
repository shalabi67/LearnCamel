package com.learn.camel_springboot.camel.translator;

import com.learn.camel_springboot.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderTranslator {
    private static final Logger LOG = LoggerFactory.getLogger(OrderTranslator.class);
    public String toJson(Map<String, Object> orderMap) {
        LOG.info("tojson started.");
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

        Order order = new Order();
        order.setId((int)getValue(orderMap, "ID"));
        order.setDescription((String)getValue(orderMap, "DESCRIPTION"));
        order.setAmount((Double)getValue(orderMap, "AMOUNT"));
        order.setProcessed((Boolean)getValue(orderMap, "PROCESSED"));


        LOG.info(order.toString());
        return order.toString();
    }
    private Object getValue(Map<String, Object> orderMap, String key) {
        if(orderMap.containsKey(key)) {
            return orderMap.get(key);
        }

        return null;
    }
}
