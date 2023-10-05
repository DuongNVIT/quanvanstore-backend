package com.quanvanstorebackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Set<String> IGNORED_FILTER_URL = new HashSet<>();

    static {
        IGNORED_FILTER_URL.add("/");
        IGNORED_FILTER_URL.add("/api/signin");
        IGNORED_FILTER_URL.add("/api/signup");
    }

    @Autowired
    private com.quanvanstorebackend.security.JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println(System.currentTimeMillis());

        boolean isIgnoreJwtFilter = IGNORED_FILTER_URL.contains(request.getRequestURI())
                || request.getRequestURI().startsWith("/api/product")
                || request.getRequestURI().startsWith("/api/news")
                || request.getRequestURI().startsWith("/api/banner")
                || request.getRequestURI().startsWith("/api/images")
                || request.getRequestURI().startsWith("/api/infor")
                || request.getRequestURI().startsWith("/api/payment/save-order");
        if(isIgnoreJwtFilter) {
            System.out.println("Không cần filter jwt");
            chain.doFilter(request, response);
            return;
        }
//        else if(!isIgnoreJwtFilter){
//            chain.doFilter(request, response);
//            return;
//        }

        System.out.println("Phải jwt filter");

        try {
            String token = getTokenFromHeader(request);
            UserDetails userDetails = getUserFromToken(token);
            jwtTokenUtil.validateToken(token, userDetails);
            saveToSecurityContext(userDetails, request);
        } catch (Exception exception) {
            logger.error("Invalid token");
            exception.printStackTrace();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ServerResponse responseDTO = new ServerResponse(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Không có quyền truy cập, token không hợp lệ",
                    null);
            new ObjectMapper().writeValue(response.getOutputStream(), responseDTO);
            return;
        }
        System.out.println("113 vào đây");
        chain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            return token;
        } else {
            System.out.println("86 token khong hop le");
            throw new InvalidTokenException("Token does not begin with bearer!");
        }
    }

    public UserDetails getUserFromToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println(94 + " " + username);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        System.out.println("++++++++");
        System.out.println(userDetails);
        System.out.println("++++++++++");
        System.out.println(userDetails);
        return userDetails;
    }

    private void saveToSecurityContext(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

}