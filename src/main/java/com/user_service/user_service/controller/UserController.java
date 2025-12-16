package com.user_service.user_service.controller;

import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody @Valid RegisterRequestDTO request){
        UserEntity user = userService.register(request);
//        return new ResponseEntity(user, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
