package com.example.demo.Validation;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class TockenValid {

        public final static long EXPIRATION_TIME = 864_000_000;
        public final static String SECRET = "Secret";

        public static String getToken(String name) {
            return Jwts.builder().setSubject(name)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        }
        public boolean Validate(String token) {
            try {
                Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
                return true;
            } catch (Exception e) {

            }
            return false;
        }

    public static String geteLoginlByToken(String token){
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return claims.getSubject();
    }
        public static String geteEmaillByToken(String token){
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
        }
    }

