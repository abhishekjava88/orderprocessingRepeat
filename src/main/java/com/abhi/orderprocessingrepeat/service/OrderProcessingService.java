package com.abhi.orderprocessingrepeat.service;

import com.abhi.orderprocessingrepeat.exception.OrderNotFoundException;
import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;
import com.abhi.orderprocessingrepeat.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderProcessingService {

    private final OrderRepository orderRepository;

   public List<Order> getOrderList(){
        return orderRepository.findAll();
   }

   public Order findOrder(long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException(("Order %d not found.".formatted(orderId))));
   }

   @Transactional
   public Order createOrder(String product, BigDecimal amount){
        Order orderToCreate = new Order(product,amount);
        Order dbOrder = orderRepository.save(orderToCreate);
        return dbOrder;
   }

   @Transactional
   public Order deleteOrder(long orderId){
        Order orderToDelete = findOrder(orderId);
        orderRepository.delete(orderToDelete);
        return orderToDelete;
   }

   @Transactional
    public Order updateOrder(long orderId, String product, BigDecimal amount, Status status){
        Order orderToUpdate = findOrder(orderId);
        orderToUpdate.setProduct(product);
        orderToUpdate.setAmount(amount);
        orderToUpdate.setStatus(status);
        return orderToUpdate;
    }
}
