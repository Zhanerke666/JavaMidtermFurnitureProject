package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderInfo;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class OrderController {
    protected OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<?> createOrder(@RequestHeader("Auth") String token,@RequestBody OrderInfo order) throws Exception{
        try{
            System.out.println(token);
            orderService.createOrder(token,order);
            System.out.println(order);
            System.out.println(order.getOrderDetailInfo());
            System.out.println(order.getTotalPrice());
             return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
        }
    }

    @GetMapping("/api/orders")
    public ResponseEntity<?> getOrdersWithOrderLists(@RequestHeader("Auth") String token) throws Exception{
        try{
            return ResponseEntity.ok(orderService.getAllOrders(token));
        }catch (Exception e){
//            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
            throw e;
        }
    }
}
