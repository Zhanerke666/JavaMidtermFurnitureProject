package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    protected OrderRepository orderRepository;
    protected OrderListRepository orderListRepository;
    protected AuthService authService;
    protected ProductRepository productRepository;
    protected RoleRepository roleRepository;
    protected CustomerRepository customerRepository;

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
        newOrder.setStatus("new");
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
                Product product = productRepository.findById ( orderList.getProductId ()).orElse ( new Product ());
                orderDetailInfo.setPrice(orderList.getPrice());
                orderDetailInfo.setName ( product.getName () );
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

    public Object getAllOrdersByStatus(String token, String status) throws Exception{
        Customer me;
        try {
            me = authService.getCustomerByToken(token);
            String role = roleRepository.findRoleByUserId(me.getId());
            if(!role.equals("admin")) throw new Exception("Not admin");
        } catch (Exception e) {
            throw e;
        }
        List<Order> orders;
        if(status.equals("all")){
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findAllByStatus(status);
        }
        if(orders.isEmpty()){
            throw new Exception("No items");
        }
        System.out.println(orders);
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (Order order:
                orders) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(order.getId());
            orderInfo.setDate(order.getOrderDate());
            orderInfo.setTotalPrice(order.getTotalPrice());
            Customer customer = customerRepository.findById(order.getCustomerId()).orElseThrow();
            orderInfo.setCustomerAddress(customer.getAddress());
            orderInfo.setCustomerName(customer.getName());
            orderInfo.setCustomerPhone(customer.getPhone());
            orderInfo.setStatus(order.getStatus());
            System.out.println(orderInfo);
            assert false;
            orderInfoList.add(orderInfo);
        }
        System.out.println(orderInfoList);
        return orderInfoList;
    }


    public Object getAllOrdersByStatusCustomer(String token, String status) throws Exception{
        Customer me;
        try {
            me = authService.getCustomerByToken(token);
            String role = roleRepository.findRoleByUserId(me.getId());
            if(!role.equals("customer")) throw new Exception("Error");
        } catch (Exception e) {
            throw e;
        }
        List<Order> orders;
        if(status.equals("all")){

            orders = orderRepository.findAllByCustomerId(me.getId());
        } else if (status.equals("new")){
            orders = orderRepository.findAllByStatusAndCustomerId(me.getId(), status);
        }else if (status.equals("inprogress")){
            orders = orderRepository.findAllByStatusAndCustomerId(me.getId(), status);
        }else if (status.equals("done")){
            orders = orderRepository.findAllByStatusAndCustomerId(me.getId(), status);
        }else {
            orders = orderRepository.findAllByStatusAndCustomerId(me.getId(), status);
        }
        if(orders.isEmpty()){
            throw new Exception("No items");
        }
        System.out.println(orders);
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (Order order:
                orders) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setId(order.getId());
            orderInfo.setDate(order.getOrderDate());
            orderInfo.setTotalPrice(order.getTotalPrice());
            Customer customer = customerRepository.findById(order.getCustomerId()).orElseThrow();
            orderInfo.setCustomerAddress(customer.getAddress());
            orderInfo.setCustomerName(customer.getName());
            orderInfo.setCustomerPhone(customer.getPhone());
            orderInfo.setStatus(order.getStatus());
            System.out.println(orderInfo);
            assert false;
            orderInfoList.add(orderInfo);
        }
        System.out.println(orderInfoList);
        return orderInfoList;
    }

    public Object getOrderWithItemsById(String token, Long id) throws Exception {
        Customer me;
        try {
            me = authService.getCustomerByToken(token);
        } catch (Exception e) {
            throw e;
        }
        Long myId = me.getId();
        System.out.println(myId);
        Order order = orderRepository.findById(id).orElseThrow();
        String role = roleRepository.findRoleByUserId(me.getId());
        System.out.println(role);
        if(!myId.equals(order.getCustomerId()) && !role.equals("admin")) throw new Exception("NO access");
        System.out.println(order);
        OrderInfo orderInfo = new OrderInfo();
        Long orderId = order.getId();
        List<OrderDetailInfo> orderDetailInfos = new ArrayList<>();
        List<OrderList> orderLists = orderListRepository.findAllByOrderId(orderId);
        System.out.println(orderLists);
        for (OrderList orderList: orderLists){
            OrderDetailInfo orderDetailInfo = new OrderDetailInfo();
            Product product = productRepository.findById ( orderList.getProductId ()).orElse ( new Product ());
            orderDetailInfo.setPrice(orderList.getPrice());
            orderDetailInfo.setId(orderList.getId());
            orderDetailInfo.setName ( product.getName () );
            orderDetailInfo.setProductId(orderList.getProductId());
            orderDetailInfo.setQuantity(orderList.getQuantity());
            orderDetailInfo.setTotalSum(orderList.getTotalSum());
            orderDetailInfos.add(orderDetailInfo);
            System.out.println(orderDetailInfos);
        }
            orderInfo.setDate(order.getOrderDate());
            orderInfo.setTotalPrice(order.getTotalPrice());
            orderInfo.setOrderDetailInfo(orderDetailInfos);
            orderInfo.setCustomerId(order.getCustomerId());
            orderInfo.setStatus(order.getStatus());
            Customer customer = customerRepository.findById(order.getCustomerId()).orElseThrow();
            orderInfo.setCustomerAddress(customer.getAddress());
            orderInfo.setCustomerName(customer.getName());
            orderInfo.setCustomerPhone(customer.getPhone());
            orderInfo.setId(order.getId());
            System.out.println(orderInfo);
        return orderInfo;
    }

    public ResponseEntity<?> setNewStatus(String token, Long id, String status) throws  Exception{
        Customer me;
        try {
            me = authService.getCustomerByToken(token);
        } catch (Exception e) {
            throw e;
        }
        Long myId = me.getId();
        System.out.println(myId);
        Order order = orderRepository.findById(id).orElseThrow();
        String role = roleRepository.findRoleByUserId(me.getId());
        System.out.println(role);
        if(!myId.equals(order.getCustomerId()) && !role.equals("admin")) throw new Exception("NO access");
        try{
            order.setStatus(status);
            return ResponseEntity.ok(orderRepository.save(order));
        }catch (Exception e){
            throw e;
        }
    }
}
