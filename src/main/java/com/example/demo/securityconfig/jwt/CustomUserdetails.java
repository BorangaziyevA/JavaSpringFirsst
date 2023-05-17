package com.example.demo.securityconfig.jwt;

import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

public class CustomUserdetails implements UserDetails {
    private String login;
    private  String password;
    private  Collection<? extends GrantedAuthority> grantedAuthorities;
    public static CustomUserdetails fromUsertoCustomerUserDetails(User user)
    {
        CustomUserdetails cU= new CustomUserdetails();
        cU.login=user.getName();
        cU.password=user.getPassword();
        cU.grantedAuthorities= Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));
   return cU;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
