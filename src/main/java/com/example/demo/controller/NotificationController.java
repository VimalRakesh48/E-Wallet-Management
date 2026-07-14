package com.example.demo.controller;

import com.example.demo.dto.NotificationRequestDTO;
import com.example.demo.dto.NotificationResponseDTO;
import com.example.demo.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponseDTO createNotification(@Valid @RequestBody NotificationRequestDTO request) {
        return notificationService.createNotification(request);
    }

    @GetMapping("/{id}")
    public NotificationResponseDTO getNotification(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping
    public List<NotificationResponseDTO> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/user/{userId}")
    public List<NotificationResponseDTO> getByUser(@PathVariable Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @PutMapping("/{id}")
    public NotificationResponseDTO update(@PathVariable Long id,
                                          @Valid @RequestBody NotificationRequestDTO request) {
        return notificationService.updateNotification(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}