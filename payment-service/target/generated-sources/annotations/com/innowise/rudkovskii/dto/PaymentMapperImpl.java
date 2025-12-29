package com.innowise.rudkovskii.dto;

import com.innowise.rudkovskii.entity.Payment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T10:42:06+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentResponse paymentToPaymentResponse(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setId( payment.getId() );
        paymentResponse.setUserId( payment.getUserId() );
        paymentResponse.setOrderId( payment.getOrderId() );
        paymentResponse.setStatus( payment.getStatus() );
        paymentResponse.setTimestamp( payment.getTimestamp() );
        paymentResponse.setAmount( payment.getAmount() );

        return paymentResponse;
    }

    @Override
    public Payment paymentRequestToPayment(PaymentRequest paymentRequest) {
        if ( paymentRequest == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setOrderId( paymentRequest.getOrderId() );
        payment.setUserId( paymentRequest.getUserId() );
        payment.setTimestamp( paymentRequest.getTimestamp() );
        payment.setAmount( paymentRequest.getAmount() );

        return payment;
    }
}
