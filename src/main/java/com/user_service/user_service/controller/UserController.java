package com.user_service.user_service.controller;

import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.dto.RegisterResponseDTO;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.mapper.UserMapper;
import com.user_service.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request){
        UserEntity user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }
}
