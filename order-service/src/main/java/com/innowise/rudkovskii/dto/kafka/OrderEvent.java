package com.innowise.rudkovskii.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent{

    private String eventId;
    private long orderId;
    private long userId;
    private double amount;
    private LocalDateTime timestamp;

}
