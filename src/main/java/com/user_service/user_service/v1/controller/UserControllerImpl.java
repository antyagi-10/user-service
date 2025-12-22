package com.user_service.user_service.v1.controller;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.LoginResponseDTO;
import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.dto.RegisterResponseDTO;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.mapper.UserMapper;
import com.user_service.user_service.service.JwtService;
import com.user_service.user_service.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserControllerImpl implements UserController{

    private final UserServiceImpl userService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request){
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
    public ResponseEntity<RegisterResponseDTO> update(@PathVariable Integer id, @RequestBody RegisterRequestDTO request) {
        UserEntity updated = userService.updateUser(id, request);
        return ResponseEntity.ok(UserMapper.toDto(updated));
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
