package com.quanvanstorebackend.config;

import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.utils.CurrentUserUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class CustomAuditAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = CurrentUserUtils.getCurrentUser();


        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        if(authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }

//        return Optional.of(((UserDetails) authentication.getPrincipal()).getUsername());
        if(userEntity == null) return null;
        return Optional.of(userEntity.getId());
    }
}
