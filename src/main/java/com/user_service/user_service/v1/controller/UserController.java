package com.user_service.user_service.v1.controller;

import com.user_service.user_service.dto.*;
import com.user_service.user_service.entity.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    @PostMapping("/register")
    ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request);

    @PostMapping("/action/logout")
    ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader);

    @PatchMapping("/{id}")
    ResponseEntity<UserResponseDTO> update(@PathVariable Integer id, @RequestBody RegisterRequestDTO request);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

    @GetMapping()
    ResponseEntity<List<UserEntity>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<UserEntity> getById(@PathVariable Integer id);

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request);

    @PostMapping("/validateToken")
    ResponseEntity<UserResponseDTO> validateToken(@RequestBody TokenRequestDTO id);

}
