package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.UserDTO;
import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.security.JwtRequest;
import com.quanvanstorebackend.security.JwtResponse;
import com.quanvanstorebackend.security.JwtUtil;
import com.quanvanstorebackend.security.StoreUserDetails;
import com.quanvanstorebackend.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtResponse signIn(JwtRequest requestPayload) {
        UsernamePasswordAuthenticationToken
                authenticationObject = new UsernamePasswordAuthenticationToken(requestPayload.getUsername(), requestPayload.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationObject);
        StoreUserDetails userDetails = (StoreUserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        String role = userDetails.getUserEntity().getRoleId() == 1 ? "admin" : "user";
        return new JwtResponse(userDetails.getUserEntity().getCode(), userDetails.getUsername(), role, token);
    }

    public UserDTO signUp(UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return createdUser;
    }

    public void changePassword(String oldPass, String newPass, String verifyPass) {
        UserEntity userEntity = CurrentUserUtils.getCurrentUser();
        System.out.println(userEntity.getPassword());
        boolean match = passwordEncoder.matches(oldPass, userEntity.getPassword());
        if(!match || !newPass.equals(verifyPass)) {
            int x = 10/0;
        }
        userEntity.setPassword(passwordEncoder.encode(newPass));
        userService.save(userEntity);

    }

    public void changeProfile(UserEntity userEntity) {
        UserEntity userEntity1 = userService.findById(userEntity.getId());
        userEntity1.setFullname(userEntity.getFullname());
        userEntity1.setEmail(userEntity.getEmail());
        userEntity1.setPhone(userEntity.getPhone());
        userService.save(userEntity1);
    }

    public UserEntity getProfile() {
        return CurrentUserUtils.getCurrentUser();
    }
}
