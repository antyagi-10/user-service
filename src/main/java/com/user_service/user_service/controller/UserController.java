package com.user_service.user_service.controller;

import com.user_service.user_service.dto.RegisterReq;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterReq req){
        UserEntity user = userService.register(req);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}
