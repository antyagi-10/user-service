package com.user_service.user_service.service;

import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.exception.EmailAlreadyExistsException;
import com.user_service.user_service.exception.UserNotFoundException;
import com.user_service.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserEntity register(RegisterRequestDTO request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword_hash(request.getPassword());
        user.setRole(UserEntity.Role.valueOf(request.getRole()));
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(Integer id, RegisterRequestDTO request){
        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not Found"));

        userRepository.updateUser(
                id,
                request.getUsername(),
                request.getEmail(),
                request.getRole() != null
                        ? UserEntity.Role.valueOf(request.getRole())
                        : null,
                request.getPassword()
        );
        return userRepository.findById(id).get();
    }

    @Override
    public boolean deleteUser(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User not Found");
        }
        userRepository.deleteById(id);
        return true;
    }
}

