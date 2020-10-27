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
    private float totalPrice;
    private String date;
    private List<OrderDetailInfo> orderDetailInfo;
}
