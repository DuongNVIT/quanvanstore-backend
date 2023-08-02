package com.quanvanstorebackend.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.quanvanstorebackend.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public String sendNoti(NotificationDTO notificationDTO) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationDTO.getTitle())
                .setBody(notificationDTO.getBody())
                .setImage(notificationDTO.getImage())
                .build();
        Message message = Message
                .builder()
                .setToken(notificationDTO.getToken())
                .setNotification(notification)
                .putAllData(notificationDTO.getData())
                .build();
        try {
            firebaseMessaging.send(message);
            return "Success sending!";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "Error sending";
        }
    }
}
