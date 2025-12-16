package com.user_service.user_service.mapper;

import com.user_service.user_service.dto.RegisterResponseDTO;
import com.user_service.user_service.entity.UserEntity;

public class UserMapper {
        public static RegisterResponseDTO toDto(UserEntity user) {
            return new RegisterResponseDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreated_at(),
                    user.getUpdated_at()
            );
        }
    }

