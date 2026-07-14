package com.example.demo.service.impl;

import com.example.demo.dto.NotificationRequestDTO;
import com.example.demo.dto.NotificationResponseDTO;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + request.getUserId()));

        Notification notification = Notification.builder()
                .title(request.getTitle())
                .message(request.getMessage())
                .isRead(request.getIsRead())
                .user(user)
                .build();

        Notification savedNotification = notificationRepository.save(notification);

        return NotificationResponseDTO.builder()
                .notificationId(savedNotification.getNotificationId())
                .title(savedNotification.getTitle())
                .message(savedNotification.getMessage())
                .isRead(savedNotification.getIsRead())
                .createdAt(savedNotification.getCreatedAt())
                .userId(savedNotification.getUser().getUserId())
                .build();
    }

    @Override
    public NotificationResponseDTO getNotificationById(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with id : " + notificationId));

        return NotificationResponseDTO.builder()
                .notificationId(notification.getNotificationId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .userId(notification.getUser().getUserId())
                .build();
    }

    @Override
    public List<NotificationResponseDTO> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(notification -> NotificationResponseDTO.builder()
                        .notificationId(notification.getNotificationId())
                        .title(notification.getTitle())
                        .message(notification.getMessage())
                        .isRead(notification.getIsRead())
                        .createdAt(notification.getCreatedAt())
                        .userId(notification.getUser().getUserId())
                        .build())
                .toList();
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {

        return notificationRepository.findByUserUserId(userId)
                .stream()
                .map(notification -> NotificationResponseDTO.builder()
                        .notificationId(notification.getNotificationId())
                        .title(notification.getTitle())
                        .message(notification.getMessage())
                        .isRead(notification.getIsRead())
                        .createdAt(notification.getCreatedAt())
                        .userId(notification.getUser().getUserId())
                        .build())
                .toList();
    }

    @Override
    public NotificationResponseDTO updateNotification(Long notificationId, NotificationRequestDTO request) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with id : " + notificationId));

        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setIsRead(request.getIsRead());

        Notification updatedNotification = notificationRepository.save(notification);

        return NotificationResponseDTO.builder()
                .notificationId(updatedNotification.getNotificationId())
                .title(updatedNotification.getTitle())
                .message(updatedNotification.getMessage())
                .isRead(updatedNotification.getIsRead())
                .createdAt(updatedNotification.getCreatedAt())
                .userId(updatedNotification.getUser().getUserId())
                .build();
    }

    @Override
    public void deleteNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with id : " + notificationId));

        notificationRepository.delete(notification);
    }
}