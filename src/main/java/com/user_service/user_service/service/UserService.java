package com.user_service.user_service.service;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.exception.UserNotFoundException;
import io.jsonwebtoken.Claims;

import java.util.List;

public interface UserService {
    UserEntity register(RegisterRequestDTO request);
    UserEntity updateUser(Integer id, RegisterRequestDTO request);
    boolean deleteUser(Integer id);
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Integer id);
    String login(LoginRequestDTO request);
    void logout(String token);

    public UserEntity validateToken(TokenRequestDTO request){
        Claims claims = jwtService.extractAllClaims(request.getToken());
        Integer userId = claims.get("id", Integer.class);
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException("Invalid Token");
        }
        return userRepository.findById(userId).get();
    }
}
