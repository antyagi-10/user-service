package com.user_service.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
