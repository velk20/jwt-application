package com.jwt.application.authentication;

import com.jwt.application.entity.UserEntity;
import com.jwt.application.repository.UserRepository;
import com.jwt.application.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class AuthServiceIT {

    @Autowired
    AuthService authService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Test
    void testRegister() {
        RegisterDTO dto = RegisterDTO.builder()
                .username("test user")
                .password("12345password")
                .email("test@email.com")
                .build();

        AuthResponse authResponse = authService.register(dto);

        assertThat(authResponse).isNotNull();
    }

    @Transactional
    @Test
    void testAuthenticate() {
        UserEntity userEntity = UserEntity.builder()
                .username("test user")
                .password(passwordEncoder.encode("12345password"))
                .email("test@email.com")
                .build();

        userRepository.save(userEntity);
        userRepository.flush();

        AuthResponse authResponse = authService.authenticate(new AuthRequest("test user", "12345password"));

        assertThat(authResponse).isNotNull();
    }
}