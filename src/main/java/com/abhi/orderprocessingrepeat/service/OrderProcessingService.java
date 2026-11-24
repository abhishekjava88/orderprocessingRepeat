package com.abhi.orderprocessingrepeat.service;

import com.abhi.orderprocessingrepeat.exception.OrderNotFoundException;
import com.abhi.orderprocessingrepeat.model.Order;
import com.abhi.orderprocessingrepeat.model.Status;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.MessageFormat;
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
        return List.copyOf(orderList);
   }

   public Order findOrder(long orderId){
        return orderList.stream().filter(order -> order.getId() == orderId).findFirst()
                .orElseThrow(()-> new OrderNotFoundException(("Order %d not found.".formatted(orderId))));
   }

   public Order createOrder(String product, BigDecimal amount){
        Order orderToCreate = new Order();
        orderToCreate.setProduct(product);
        orderToCreate.setAmount(amount);
        orderToCreate.setStatus(Status.PENDING);
        orderToCreate.setId(orderList.size()+1);
        orderList.add(orderToCreate);
        return orderToCreate;
   }

   public Order deleteOrder(long orderId){
        Order orderToDelete = findOrder(orderId);
        orderList.remove(orderToDelete);
        return orderToDelete;
   }

    public Order updateOrder(long orderId, String product, BigDecimal amount, Status status){
        Order orderToUpdate = findOrder(orderId);
        orderToUpdate.setProduct(product);
        orderToUpdate.setAmount(amount);
        orderToUpdate.setStatus(status);
        return orderToUpdate;
    }
}
