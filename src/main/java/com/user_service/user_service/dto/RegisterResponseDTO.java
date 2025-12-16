package com.user_service.user_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterResponseDTO {
    private Integer id;
    private String email;
    private LocalDate created_at;
    private LocalDate updated_at;
}
