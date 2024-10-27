package com.jwt.application.authentication;

import com.jwt.application.entity.UserEntity;
import com.jwt.application.entity.UserRoleEntity;
import com.jwt.application.repository.UserRepository;
import com.jwt.application.repository.UserRoleRepository;
import com.jwt.application.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository, UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterDTO dto) {
        UserRoleEntity userRoleEntity = userRoleRepository.findUserRoleByUserRole(dto.getRole()).orElseThrow(() -> new RuntimeException("No User Role found!"));


        UserEntity user = UserEntity.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .createdAt(LocalDateTime.now())
                .lastModified(LocalDateTime.now())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .userRole(userRoleEntity)
                .isActive(true)
                .build();

        userRepository.save(user);
        var jwtToken = jwtUtil.generateToken(user.getUsername());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.getUserByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtUtil.generateToken(user.getUsername());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
