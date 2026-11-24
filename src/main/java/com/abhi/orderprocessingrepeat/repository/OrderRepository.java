package com.abhi.orderprocessingrepeat.repository;

import com.abhi.orderprocessingrepeat.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
