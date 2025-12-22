package com.user_service.user_service.v1.controller;

import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.dto.RegisterResponseDTO;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.mapper.UserMapper;
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

    @Override
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request){
        UserEntity user = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @Override
    public ResponseEntity<RegisterResponseDTO> update(@PathVariable Integer id, @RequestBody RegisterRequestDTO request) {
        UserEntity updated = userService.updateUser(id, request);
        return ResponseEntity.ok(UserMapper.toDto(updated));
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Integer id) {
        UserEntity entry = userService.getUserById(id);
        return ResponseEntity.ok(entry);
    }

}
