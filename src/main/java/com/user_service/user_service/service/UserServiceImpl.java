package com.user_service.user_service.service;

import com.user_service.user_service.dto.LoginRequestDTO;
import com.user_service.user_service.dto.RegisterRequestDTO;
import com.user_service.user_service.entity.TokenEntity;
import com.user_service.user_service.entity.UserEntity;
import com.user_service.user_service.exception.EmailAlreadyExistsException;
import com.user_service.user_service.exception.UserNotFoundException;
import com.user_service.user_service.repository.TokenRepository;
import com.user_service.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(RegisterRequestDTO request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword_hash(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserEntity.Role.valueOf(request.getRole()));
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String jwt = jwtService.generateToken(authentication.getName());
        UserEntity user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        revokeAllUserTokens(user);

        TokenEntity token = new TokenEntity();
        token.setToken(jwt);
        token.setTokenType(TokenEntity.TokenType.Bearer);
        token.setExpired(false);
        token.setRevoked(false);
        token.setUser(user);
        tokenRepository.save(token);

        return jwt;
    }

    private void revokeAllUserTokens(UserEntity user) {
        List<TokenEntity> validTokens =
                tokenRepository.findAllValidTokens(user.getId());
        if (validTokens.isEmpty()) return;
        validTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    @Override
    public void logout(String token) {
        TokenEntity savedToken = tokenRepository
                .findValidToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        savedToken.setExpired(true);
        savedToken.setRevoked(true);
        tokenRepository.save(savedToken);
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
        UserEntity user = userRepository.findById(id)
              .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Integer id) {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException("User not Found");
        }
        return userRepository.findById(id).get();
    }

}

