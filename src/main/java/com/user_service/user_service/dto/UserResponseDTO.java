package com.user_service.user_service.dto;

import com.user_service.user_service.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private UserEntity.Role role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
