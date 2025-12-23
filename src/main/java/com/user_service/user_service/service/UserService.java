package com.user_service.user_service.service;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.dto.TokenRequestDTO;
import com.user_service.user_service.entity.UserEntity;
import java.util.List;

public interface UserService {
    UserEntity register(RegisterRequestDTO request);
    UserEntity updateUser(Integer id, RegisterRequestDTO request);
    boolean deleteUser(Integer id);
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Integer id);
    String login(LoginRequestDTO request);
    void logout(String token);
    UserEntity validateToken(TokenRequestDTO request);
}
