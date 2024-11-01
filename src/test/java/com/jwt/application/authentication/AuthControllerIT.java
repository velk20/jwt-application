package com.jwt.application.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.application.entity.UserEntity;
import com.jwt.application.repository.UserRepository;
import com.jwt.application.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIT {

    @Value("${jwt.secret.key}")
    private String secret;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @MockBean
    AuthService authService;

    @Transactional
    @Test
    void register() throws Exception {
        RegisterDTO dto = RegisterDTO.builder()
                .username("test user")
                .password("12345password")
                .email("test@email.com")
                .build();

        when(authService.register(dto)).thenReturn(new AuthResponse(jwtUtil.generateToken(dto.getUsername())));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andReturn();

        // Assert successful registration
        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        MockHttpServletResponse responseBody = result.getResponse();

        String body = responseBody.getContentAsString();
        String token = body.split(":")[1].replace("\"", "");

        System.out.println(token);
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        assertEquals(username, dto.getUsername());
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Transactional
    @Test
    void testValidAuthentication() throws Exception {
        UserEntity testUserEntity = new UserEntity()
                .setUsername("Test user")
                .setPassword("12345ABc")
                .setEmail("test@email.com");

        userRepository.save(testUserEntity);
        userRepository.flush();

        AuthRequest userRequest = new AuthRequest("Test user", "12345ABc");
        when(authService.authenticate(userRequest)).thenReturn(AuthResponse.builder().token("").build());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }
}