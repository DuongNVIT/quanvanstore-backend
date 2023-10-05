package com.quanvanstorebackend.controller;

import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.dto.UserDTO;
import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.exception.*;
import com.quanvanstorebackend.security.JwtRequest;
import com.quanvanstorebackend.security.JwtResponse;
import com.quanvanstorebackend.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ServerResponse signin(@RequestBody JwtRequest requestPayload) {
        try {
            JwtResponse response = authService.signIn(requestPayload);
            return ServerResponse.success("Đăng nhập thành công!", response);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new WrongUsernamPasswordException("Sai tên đăng nhập hoặc mật khẩu");
        } catch (InsufficientAuthenticationException e) {
            throw new UnauthorizeException("Không có quyền truy cập!");
        } catch (DisabledException exception) {
            throw new DisableAccountException("Tài khoản chưa kích hoạt");
        } catch (Exception e) {
            log.error("Error login!");
            throw new UnknowException("Unknow exception!");
        }
    }

    @PostMapping("/signup")
    public ServerResponse signup(@RequestBody UserDTO userDTO) {
        try {
            UserDTO userDto = authService.signUp(userDTO);
            return ServerResponse.success("Đăng ký thành công!",userDto);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateAccountException("Trùng tên đăng nhập!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnauthorizeException("Unknow exception!");
        }
    }

    @PutMapping("/change-pass")
    public ServerResponse changePassword(@RequestParam String oldPass,
                                         @RequestParam String newPass,
                                         @RequestParam String verifyPass) {
        try {
            authService.changePassword(oldPass, newPass, verifyPass);
            return ServerResponse.success("Đổi mật khẩu");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception change pass!");
        }
    }

    @PutMapping("/profile")
    public ServerResponse changeProfile(@RequestBody UserEntity userEntity) {
        try {
            authService.changeProfile(userEntity);
            return ServerResponse.success("change profile");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception change profile!");
        }
    }

    @GetMapping("/profile")
    public ServerResponse getProfile() {
        try {
            UserEntity profile = authService.getProfile();
            return ServerResponse.success("Get profile", profile);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception change profile!");
        }
    }
}
