package com.user_service.user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public RegisterResponseDTO(Integer id, String username, String email, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
