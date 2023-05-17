package com.example.demo.requests;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String login;
    private String password;
    private String email;
    private int age;
    private String gender;
}
