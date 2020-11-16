package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long customerId);
    List<Order> findAllByStatus(String status);

    @Query(value = "select customer_id and status from orderr ",nativeQuery = true)
    List<Order> findAllByStatusAndCustomerId(Long customerId, String status);
}
