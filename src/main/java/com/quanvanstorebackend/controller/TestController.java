package com.quanvanstorebackend.controller;

import com.quanvanstorebackend.dto.NotificationDTO;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.FirebaseMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private FirebaseMessagingService firebaseMessagingService;

    @PostMapping("/")
    public String testPushNoti(@RequestBody NotificationDTO notificationDTO) {
        try {
            System.out.println(notificationDTO.getToken());
            return firebaseMessagingService.sendNoti(notificationDTO);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception test push noti!");
        }
    }
}
