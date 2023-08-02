package com.quanvanstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private String token;
    private String title;
    private String body;
    private String image;
    private Map<String, String> data = new HashMap<>();
}
