package com.user_service.user_service.v1.controller;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.LoginResponseDTO;
import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.dto.RegisterResponseDTO;
import com.user_service.user_service.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    @PostMapping("/register")
    ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request);

    @PostMapping("/action/logout")
    public ResponseEntity<?> logout(
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = authorizationHeader.substring(7);
        userService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }

    @PatchMapping("/{id}")
    ResponseEntity<RegisterResponseDTO> update(@PathVariable Integer id, @RequestBody RegisterRequestDTO request);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping()
    ResponseEntity<List<UserEntity>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<UserEntity> getById(@PathVariable Integer id);

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request);
}
