package com.example.demo.service;

import com.example.demo.entity.OrderList;
import com.example.demo.repository.OrderListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Service
public class OrderListService {
    protected OrderListRepository orderListRepository;



    public OrderList save(OrderList orderList) throws Exception{
        try {
            return orderListRepository.save(orderList);
        }catch (Exception e){
             throw e;
        }
    }
};

@DeleteMapping("/api/orders/delete-dashboard/{orderId}")
public void deleteDashboard(@PathVariable Long orderId){
        OrderListService.deleteFromDashboard(orderId);
        }

