package com.abhi.orderprocessingrepeat.dto;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;

import java.math.BigDecimal;

public record OrderResponse(long id, String product, BigDecimal amount, Status status) {
    public static OrderResponse from(Order order){
        return new OrderResponse(order.getId(),order.getProduct(),order.getAmount(),order.getStatus());
    }
}
