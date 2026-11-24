package com.abhi.orderprocessingrepeat.controller;

import com.abhi.orderprocessingrepeat.dto.CreateOrderRequest;
import com.abhi.orderprocessingrepeat.dto.OrderResponse;
import com.abhi.orderprocessingrepeat.dto.UpdateOrderRequest;
import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.abhi.orderprocessingrepeat.dto.OrderResponse.from;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> getOrders(){
        return ResponseEntity.ok().body(orderProcessingService.getOrderList().stream().map(
                order -> from(order)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable long id){
        return ResponseEntity.ok(from(orderProcessingService.findOrder(id)));
    }

    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        Order createdOrder = orderProcessingService.createOrder(createOrderRequest.product(),createOrderRequest.amount());
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(createdOrder));
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable long id, @RequestBody UpdateOrderRequest updateOrderRequest){
        Order updatedOrder = orderProcessingService.updateOrder(id,updateOrderRequest.product(),updateOrderRequest.amount(),updateOrderRequest.status());
        return ResponseEntity.ok(OrderResponse.from(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable long id){
        return ResponseEntity.ok(from(orderProcessingService.deleteOrder(id)));
    }
}
