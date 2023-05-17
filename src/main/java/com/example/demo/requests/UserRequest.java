package com.example.demo.requests;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRequest {
 private Integer id;
 private String name;
 private String email;
 private String password;
 private String login;



}