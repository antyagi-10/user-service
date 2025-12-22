package com.user_service.user_service.service;

import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.entity.UserEntity;

public interface UserService {
    UserEntity register(RegisterRequestDTO request);
}
