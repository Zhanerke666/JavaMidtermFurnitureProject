package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailInfo {
    private Long productId;
    private float price;
    private float totalSum;
    private int quantity;
}
