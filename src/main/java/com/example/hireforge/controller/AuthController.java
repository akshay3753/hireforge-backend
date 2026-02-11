package com.example.hireforge.controller;

import com.example.hireforge.dto.LoginRequest;
import com.example.hireforge.dto.RegisterRequest;
import com.example.hireforge.entity.User;
import com.example.hireforge.security.JwtService;
import com.example.hireforge.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

        User user = userService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok("User registered with id: " + user.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return ResponseEntity.ok(token);
    }
    @GetMapping("/me")
    public ResponseEntity<?> me() {
        return ResponseEntity.ok("You are authenticated");
    }

}
