package com.example.demo.requests;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ProductRequest {
    private  String name;

    private Integer id;
    private String price;
    private Date date;







}
