package com.abhi.orderprocessingrepeat.service;

import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.*;

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
        return Collections.unmodifiableList(orderList);
   }

   public Optional<Order> findOrder(long orderId){
        return orderList.stream().filter(order -> order.id() == orderId).findFirst();
   }

   public List<Order> createOrder(Order order){
        orderList.add(order);
        return List.copyOf(orderList);
   }

   public Optional<Order> deleteOrder(long orderId){
       Optional<Order> result = findOrder(orderId);
       return result.map(order -> {
           orderList.remove(order);
           return Optional.of(order);
       }).orElse(Optional.empty());
   }

    public Optional<Order> updateOrder(long id, Order newOrder){
        Optional<Order> result = orderList.stream().filter(order -> order.id() == id).findFirst();
        return result.map(order -> {
            orderList.remove(order);
            Order orderToUpdate = new Order(id, newOrder.product(), newOrder.amount(), newOrder.status());
            orderList.add(orderToUpdate);
            return Optional.of(orderToUpdate);
        }).orElse(Optional.empty());
    }
}
