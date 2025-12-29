package com.innowise.rudkovskii.service.message;

import com.innowise.rudkovskii.dto.kafka.PaymentEvent;
import com.innowise.rudkovskii.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentEventConsumer {

    private static final String TOPIC = "payment-created";
    private final OrderService orderService;

    @Autowired
    public PaymentEventConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = TOPIC,
            groupId = "order-service-group"
    )
    public void handlePaymentCreated(PaymentEvent event){
        log.info("Received message: {}", event);
        //orderService.updateOrderStatus(event.getOrderId(), event.getStatus());
    }

    @PostConstruct
    public void init() {
        System.out.println("✅ PaymentEventConsumer создан");
    }
}
