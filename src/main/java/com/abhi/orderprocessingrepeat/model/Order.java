package com.abhi.orderprocessingrepeat.model;

import java.math.BigDecimal;

public record Order(long id, String product, BigDecimal amount, Status status) {
}
