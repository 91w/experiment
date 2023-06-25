package com.wgh.backend.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wgh.backend.config.utils.JwtUtil;
import com.wgh.backend.mapper.UserMapper;
import com.wgh.backend.pojo.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
//import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
        @Autowired
        private UserMapper userMapper;

        @Override
        protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException, ServletException {
            String token = request.getHeader("Authorization");

            if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            token = token.substring(7);

            String username;
            try {
                username = JwtUtil.extractUsername(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User loginUser = userMapper.selectOne(queryWrapper);

            if (loginUser == null) {
                throw new RuntimeException("用户名未登录");
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, null);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        }
    }


