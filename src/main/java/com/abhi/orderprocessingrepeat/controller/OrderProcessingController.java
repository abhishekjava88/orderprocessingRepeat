package com.abhi.orderprocessingrepeat.controller;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok().body(orderProcessingService.getOrderList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable long id){
        return orderProcessingService.findOrder(id)
                .map(order -> ResponseEntity.ok(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<List<Order>> createOrder(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderProcessingService.createOrder(order));
    }


    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
       return orderProcessingService.updateOrder(order)
               .map(order1 -> ResponseEntity.ok(order1))
               .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable long id){
        return orderProcessingService.deleteOrder(id)
                .map(order1 -> ResponseEntity.ok(order1))
                .orElse(ResponseEntity.notFound().build());
    }
}
