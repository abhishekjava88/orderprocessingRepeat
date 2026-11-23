package com.abhi.orderprocessingrepeat.service;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class OrderProcessingService {

    private List<Order> orderList;

    OrderProcessingService(){
        orderList = new ArrayList<>();
        orderList.add( new Order(1, "Fruits", BigDecimal.valueOf(58.0), Status.SHIPPED));
        orderList.add(  new Order(2, "Coke", BigDecimal.valueOf(26.0), Status.SHIPPED));
        orderList.add( new Order(3, "books", BigDecimal.valueOf(87.23), Status.SHIPPED));
    }

   public List<Order> getOrderList(){
        return orderList;
   }

   public Optional<Order> findOrder(long orderId){
        return orderList.stream().filter(order -> order.id() == orderId).findFirst();
   }

   public List<Order> createOrder(Order order){
        orderList.add(order);
        return orderList;
   }

   public Optional<Order> deleteOrder(long orderId){
      Optional<Order> orderToDelete = findOrder(orderId);
      if(orderToDelete.isPresent()){
          orderList.remove(orderToDelete.get());
          return orderToDelete;
      }
      return Optional.empty();
   }

    public Optional<Order> updateOrder(Order order){
        Optional<Order> existingOrder = orderList.stream().filter(ord -> order.id() == order.id()).findFirst();
        if(existingOrder.isPresent()){
            orderList.remove(existingOrder);
            orderList.add(order);
            return Optional.of(order);
        }
        return Optional.empty();
    }

}
