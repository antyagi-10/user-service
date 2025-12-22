package com.user_service.user_service.v1.controller;

import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.dto.RegisterResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    @PostMapping("/register")
    ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request);
}
