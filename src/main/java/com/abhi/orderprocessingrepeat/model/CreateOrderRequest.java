package com.abhi.orderprocessingrepeat.model;

import java.math.BigDecimal;

public record CreateOrderRequest(String product, BigDecimal amount) {
}
