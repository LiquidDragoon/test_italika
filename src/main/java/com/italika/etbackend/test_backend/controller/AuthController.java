package com.italika.etbackend.test_backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italika.etbackend.test_backend.jwt.AuthRequest;
import com.italika.etbackend.test_backend.jwt.AuthResponse;
import com.italika.etbackend.test_backend.jwt.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails user = userDetailsService.loadUserByUsername(request.getUserName());
        String token = jwtUtil.generarToken(user.getUsername());
        return new AuthResponse(token);
    }
}