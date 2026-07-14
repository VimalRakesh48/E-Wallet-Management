package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long userId);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO);

    void deleteUser(Long userId);
}