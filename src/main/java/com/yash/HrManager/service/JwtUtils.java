package com.yash.HrManager.service;

import com.yash.HrManager.Entity.enums.UserRoles;
import com.yash.HrManager.Entity.Trainer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {
    private  final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private  final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public  String generateToken(Trainer trainer) {
        return Jwts.builder()
                .setSubject(trainer.getEmailId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public  boolean validateTokenForUser(Trainer trainer, String token) {
        try {
            Claims claims = validateToken(token);
            String emailId = claims.getSubject();
            return emailId.equals(trainer.getEmailId());
        } catch (JwtException e) {
            return false;
        }
    }

    public  boolean validateTokenForUserRole(Trainer trainer,String token, UserRoles userRole) {
        try {
            Claims claims = validateToken(token);
            String emailId = claims.getSubject();
            return (emailId.equals(trainer.getEmailId()) && (userRole.equals(trainer.getRole())));
        } catch (JwtException e) {
            return false;
        }
    }
}
