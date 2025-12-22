package com.user_service.user_service.mapper;

import com.user_service.user_service.dto.UserResponseDTO;
import com.user_service.user_service.entity.UserEntity;

public class UserMapper {
    public static UserResponseDTO toDto(UserEntity user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreated_at(),
                user.getUpdated_at()
        );
    }
}

