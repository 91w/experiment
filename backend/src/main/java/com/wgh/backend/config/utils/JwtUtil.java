package com.wgh.backend.config.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Component
public class JwtUtil {
    public static final long JWT_ACCESS = 60 * 60 * 1000L ; //有效期1h
    public static final long JWT_REFRESH = 60 * 60 * 1000L * 24 * 14;  // 有效期14天
    public static final String JWT_KEY = "JSDFSDFSDFASJDHASDASDdfa32dJHASFDA67765asda123";


    // 生成Access Token
    public static String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, JWT_ACCESS);
    }

    // 生成Refresh Token
    public static String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, JWT_REFRESH);
    }

    // 生成Token
    private static String generateToken(UserDetails userDetails, long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername()); // 以什么作为加密者
        claims.put("iat", new Date());  // 发布时间
        claims.put("exp", new Date(System.currentTimeMillis() + expiration));  // 过期时间

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, JWT_KEY)
                .compact();
    }


    // 验证Token并返回用户信息
    public static UserDetails validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) ? userDetails : null;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 验证Token是否过期
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 从Token中提取用户名
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 解析Token的Claims
    private static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


