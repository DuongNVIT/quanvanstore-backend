package com.quanvanstorebackend.utils;

import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.security.StoreUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtils {

    public static UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            StoreUserDetails userDetails = (StoreUserDetails) authentication.getPrincipal();
            return userDetails.getUserEntity();
        } catch (Exception e) {
            return null;
        }
    }
}
