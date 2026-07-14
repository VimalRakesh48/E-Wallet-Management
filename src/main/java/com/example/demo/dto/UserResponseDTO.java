package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long userId;

    private String fullName;

    private String email;

    private String phone;

    private String role;

    private String status;
}