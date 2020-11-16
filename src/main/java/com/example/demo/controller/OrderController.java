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

    @GetMapping("/api/orders/status/{status}")
    public ResponseEntity<?> getOrdersByStatus(@RequestHeader("Auth") String token,@PathVariable String status) throws Exception{
        try{
            return ResponseEntity.ok(orderService.getAllOrdersByStatus(token,status));
        }catch (Exception e){
//            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
            throw e;
        }
    }
    @GetMapping("/api/orders/status/customer/{status}")
    public ResponseEntity<?> getOrdersByStatusCustomer(@RequestHeader("Auth") String token,@PathVariable String status) throws Exception{
        try{
            return ResponseEntity.ok(orderService.getAllOrdersByStatusCustomer(token,status));
        }catch (Exception e){
//            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
            throw e;
        }
    }



    @GetMapping("/api/orders/{id}")
    public ResponseEntity<?> getOrderById(@RequestHeader("Auth") String token,@PathVariable Long id) throws Exception{
        try{
            return ResponseEntity.ok(orderService.getOrderWithItemsById(token,id));
        }catch (Exception e){
//            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
            throw e;
        }
    }

    @PutMapping("/api/orders/{id}/status/{status}")
    public ResponseEntity<?> setNewOrderStatus(@RequestHeader("Auth") String token,@PathVariable(name = "id") Long id,@PathVariable(name = "status") String status) throws Exception{
        try{
            return ResponseEntity.ok(orderService.setNewStatus(token,id,status));
        }catch (Exception e){
//            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
            throw e;
        }
    }

}

    @GetMapping("/api/orders/get-dashboard")
    public ResponseEntity<?> createDashboard() {
        return ResponseEntity.ok(orderService.createDashboard());
    }

    @DeleteMapping("/api/orders/delete-dashboard/{orderId}")
    public void deleteDashboard(@PathVariable Long orderId){
        orderService.deleteFromDashboard(orderId);
    }

    @DeleteMapping("/api/orders/delete/{orderId}")
    public void deleteById(@PathVariable Long orderId){
        orderService.delete(orderId);

    }
