package com.innowise.rudkovskii.service.message;

import com.innowise.rudkovskii.dto.kafka.OrderEvent;
import com.innowise.rudkovskii.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderEventProducer {
    private static final String TOPIC = "order-created";

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Autowired
    public OrderEventProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(Order order) {
        OrderEvent event = new OrderEvent();
        event.setEventId((long) (Math.random() * 100000) + "");
        event.setOrderId(order.getId());
        event.setUserId(order.getUserId());
        event.setAmount(order
                .getItems()
                .stream()
                .filter(oi -> oi
                        .getOrder()
                        .equals(order))
                .map(oi -> oi
                        .getItem()
                        .getPrice())
                .mapToDouble(Double::doubleValue)
                .sum());
        event.setTimestamp(LocalDateTime.now());

        kafkaTemplate.send(TOPIC, event);
    }
}