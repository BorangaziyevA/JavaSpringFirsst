package com.example.demo.controllers;

import com.example.demo.AuthResponse;
import com.example.demo.Validation.TockenValid;
import com.example.demo.entity.User;
import com.example.demo.requests.AuthRequest;
import com.example.demo.requests.RegistrationRequest;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/reg")
    public String registrUser(@RequestBody RegistrationRequest registrationRequest)
    {
        User user=new User();
        user.setPassword(registrationRequest.getPassword());
        user.setName(registrationRequest.getLogin());
        user.setEmail(registrationRequest.getEmail());
        user.setAge(registrationRequest.getAge());
        user.setGender(registrationRequest.getGender());
        userService.saveUserReg(user);
        return "You success registrated";
    }
    @PostMapping ("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request)
    {
User user =userService.findByLoginandPassword(request.getLogin(),request.getPassword());
String token= TockenValid.getToken(user.getName());
return new AuthResponse((token));
    }
}
