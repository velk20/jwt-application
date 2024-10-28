package com.jwt.application.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Service API", description = "Register or Authenticate user")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @Operation(summary = "Register user")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(value = "/authenticate", consumes = "application/json")
    @Operation(summary = "Authentication user")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
