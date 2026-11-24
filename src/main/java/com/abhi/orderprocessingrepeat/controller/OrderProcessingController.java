package com.abhi.orderprocessingrepeat.controller;

import com.abhi.orderprocessingrepeat.model.CreateOrderRequest;
import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.OrderResponse;
import com.abhi.orderprocessingrepeat.model.Status;
import com.abhi.orderprocessingrepeat.service.OrderProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.abhi.orderprocessingrepeat.model.OrderResponse.from;
import java.util.List;
import java.util.Optional;

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
        Order orderToCreate = new Order();
        orderToCreate.setProduct(createOrderRequest.product());
        orderToCreate.setAmount(createOrderRequest.amount());
        orderToCreate.setStatus(Status.PENDING);
        return ResponseEntity.ok(from(orderProcessingService.createOrder(orderToCreate)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable long id, @RequestBody Order order){
        return ResponseEntity.ok(from(orderProcessingService.updateOrder(id,order)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable long id){
        return ResponseEntity.ok(from(orderProcessingService.deleteOrder(id)));
    }
}
