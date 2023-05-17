package com.example.demo.requests;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
    private String email;
    private String age;
    private String gender;
}
