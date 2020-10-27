package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList,Long> {
    List<OrderList> findAllByOrderId(Long orderId);
}
