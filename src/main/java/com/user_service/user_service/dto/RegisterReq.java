package com.user_service.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterReq {
    @NotNull(message = "Username cannot be null")
    private String username;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Name must have at least 8 characters")
    private String password;
    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Role cannot be null")
    private String role;
}
