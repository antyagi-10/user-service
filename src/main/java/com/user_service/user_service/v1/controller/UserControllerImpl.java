package com.user_service.user_service.v1.controller;

import com.user_service.user_service.dto.*;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.mapper.UserMapper;
import com.user_service.user_service.service.JwtService;
import com.user_service.user_service.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserControllerImpl implements UserController{

    private final UserServiceImpl userService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request){
        UserEntity user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @Override
    public   ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        String token = userService.login(request);
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return  ResponseEntity.ok(loginResponse);
    }

    @Override
    public ResponseEntity<?> logout(
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }
        String token = authorizationHeader.substring(7);
        userService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }

    @Override
    public ResponseEntity<UserResponseDTO> update(@PathVariable Integer id, @RequestBody RegisterRequestDTO request) {
        UserEntity updated = userService.updateUser(id, request);
        return ResponseEntity.ok(UserMapper.toDto(updated));
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserEntity> getById(@PathVariable Integer id) {
        UserEntity entry = userService.getUserById(id);
        return ResponseEntity.ok(entry);
    }
    public ResponseEntity<UserResponseDTO> validateToken(@RequestBody TokenRequestDTO id){
        UserEntity user = userService.validateToken(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

}
