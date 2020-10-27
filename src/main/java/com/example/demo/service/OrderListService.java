package com.example.demo.service;

import com.example.demo.entity.OrderList;
import com.example.demo.repository.OrderListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
}
