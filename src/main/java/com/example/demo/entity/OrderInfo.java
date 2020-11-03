package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInfo {
    private long id;
    private float totalPrice;
    private String date;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private List<OrderDetailInfo> orderDetailInfo;
    private String status;
}
