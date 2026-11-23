package com.abhi.orderprocessingrepeat.controller;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;
import com.abhi.orderprocessingrepeat.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orderList = orderProcessingService.getOrderList();
        return ResponseEntity.ok().body(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrder(@PathVariable long id){
        Optional<Order> result = orderProcessingService.findOrder(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<List<Order>> createOrder(@RequestBody Order order){
        List<Order> orderList = orderProcessingService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderList);
    }


    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Optional<Order> result = orderProcessingService.updateOrder(order);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable long id){
        Optional<Order> result = orderProcessingService.deleteOrder(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

}
