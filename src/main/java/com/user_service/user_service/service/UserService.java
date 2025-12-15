package com.user_service.user_service.service;

import com.user_service.user_service.dto.RegisterReq;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity register(RegisterReq req){
        if(userRepository.findByEmail(req.getEmail()).isPresent()){
            throw new RuntimeException("Email already exist");
        }

        UserEntity user = new UserEntity();
        user.setUsername(req.getUsername());
        user.setPassword_hash(req.getPassword());
        user.setRole(UserEntity.Role.valueOf(req.getRole()));
        user.setEmail(req.getEmail());
        return userRepository.save(user);
    }

}

