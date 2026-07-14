package com.example.demo.service;

import com.example.demo.dto.NotificationRequestDTO;
import com.example.demo.dto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO notificationRequestDTO);

    NotificationResponseDTO getNotificationById(Long notificationId);

    List<NotificationResponseDTO> getAllNotifications();

    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);

    NotificationResponseDTO updateNotification(Long notificationId, NotificationRequestDTO notificationRequestDTO);

    void deleteNotification(Long notificationId);
}