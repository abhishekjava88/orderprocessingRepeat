package com.abhi.orderprocessingrepeat.dto;

import com.abhi.orderprocessingrepeat.model.Status;

import java.math.BigDecimal;

public record UpdateOrderRequest(String product, BigDecimal amount, Status status) {
}
