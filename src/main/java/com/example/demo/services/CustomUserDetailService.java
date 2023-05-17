package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.securityconfig.jwt.CustomUserdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public CustomUserdetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getLogin(login);
        return CustomUserdetails.fromUsertoCustomerUserDetails(user);
    }
}
