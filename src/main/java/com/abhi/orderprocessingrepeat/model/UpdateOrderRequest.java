package com.abhi.orderprocessingrepeat.model;

import java.math.BigDecimal;

public record UpdateOrderRequest(String product, BigDecimal amount, Status status) {
}
