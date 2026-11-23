package com.abhi.orderprocessingrepeat.controller;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class OrderProcessingController {

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orderList = List.of(
        new Order(1, "Fruits", BigDecimal.valueOf(58), Status.SHIPPED)
        , new Order(2, "Coke", BigDecimal.valueOf(26), Status.SHIPPED)
        , new Order(3, "Books", BigDecimal.valueOf(26), Status.SHIPPED));

        return ResponseEntity.ok().body(orderList);
    }

}
