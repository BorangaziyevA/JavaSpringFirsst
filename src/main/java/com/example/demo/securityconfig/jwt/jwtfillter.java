package com.example.demo.securityconfig.jwt;
import com.example.demo.Validation.TockenValid;
import com.example.demo.services.CustomUserDetailService;
import io.jsonwebtoken.lang.Strings;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
@Log
public class jwtfillter extends GenericFilterBean {
    public static final String AUTHORIZATION="Authorization";
    @Autowired
    private TockenValid tockenValid;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("do filter");
        String token =getTokenRequest((HttpServletRequest) servletRequest);
        if(token!=null && tockenValid.Validate(token))
        {
            String userLogin= TockenValid.geteLoginlByToken(token);
            CustomUserdetails customUserdetails=customUserDetailService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(customUserdetails,null, customUserdetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    private String getTokenRequest(HttpServletRequest request)
    {
        String bear=request.getHeader(AUTHORIZATION);
        if(hasText(bear) && bear.startsWith("Bearer"))
        {
            return bear.substring(7);
        }
        return null;
    }
}
