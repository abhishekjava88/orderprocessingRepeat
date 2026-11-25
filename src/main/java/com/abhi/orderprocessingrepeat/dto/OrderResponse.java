package com.abhi.orderprocessingrepeat.dto;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderResponse(Long id, String product, BigDecimal amount, Status status, Instant createdAt, Instant updatedAt) {
    public static OrderResponse from(Order order){
        return new OrderResponse(order.getId(),order.getProduct(),order.getAmount(),order.getStatus(), order.getCreatedAt(), order.getUpdatedAt());
    }
}
