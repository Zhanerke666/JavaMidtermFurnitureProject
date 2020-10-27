package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.OrderListRepository;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    protected OrderRepository orderRepository;
    protected OrderListRepository orderListRepository;
    protected AuthService authService;

    public ResponseEntity<?> createOrder(String token, OrderInfo order){
        Customer me;
        try {
            me = authService.getCustomerByToken(token);
            System.out.println(me);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED ).build();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        Order newOrder = new Order();
        newOrder.setCustomerId(me.getId());
        newOrder.setOrderDate(dtf.format(localDate));
        newOrder.setTotalPrice(order.getTotalPrice());
        System.out.println(newOrder);
        try{
            newOrder = orderRepository.save(newOrder);
            System.out.println(newOrder);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Long newOrderId = newOrder.getId();
        for (OrderDetailInfo item:
             order.getOrderDetailInfo()) {
            OrderList orderList = new OrderList();
            orderList.setOrderId(newOrderId);
            orderList.setPrice(item.getPrice());
            orderList.setProductId(item.getProductId());
            orderList.setQuantity(item.getQuantity());
            orderList.setTotalSum(item.getTotalSum());
            try{
                orderListRepository.save(orderList);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            };
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<OrderInfo> getAllOrders(String token) throws Exception{
        Customer me;
        List<OrderInfo> orderInfoList = new ArrayList<>();
        try {
            me = authService.getCustomerByToken(token);
        } catch (Exception e) {
            throw e;
        }
        Long myId = me.getId();
        System.out.println(myId);
        List<Order> orders = orderRepository.findAllByCustomerId(myId);
        System.out.println(orders);
        for (Order order:
             orders) {
            OrderInfo orderInfo = new OrderInfo();
            Long orderId = order.getId();
            List<OrderDetailInfo> orderDetailInfos = new ArrayList<>();
            List<OrderList> orderLists = orderListRepository.findAllByOrderId(orderId);
            System.out.println(orderLists);
            for (OrderList orderList: orderLists){
                OrderDetailInfo orderDetailInfo = new OrderDetailInfo();
                orderDetailInfo.setPrice(orderList.getPrice());
                orderDetailInfo.setProductId(orderList.getProductId());
                orderDetailInfo.setQuantity(orderList.getQuantity());
                orderDetailInfo.setTotalSum(orderList.getTotalSum());
                orderDetailInfos.add(orderDetailInfo);
                System.out.println(orderDetailInfos);
            }
            orderInfo.setDate(order.getOrderDate());
            orderInfo.setTotalPrice(order.getTotalPrice());
            orderInfo.setOrderDetailInfo(orderDetailInfos);
            System.out.println(orderInfo);
            assert false;
            orderInfoList.add(orderInfo);
        }
        System.out.println(orderInfoList);
        return orderInfoList;
    }
}
