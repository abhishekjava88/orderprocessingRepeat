package com.abhi.orderprocessingrepeat.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(String product, BigDecimal amount) {
}
