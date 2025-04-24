package com.italika.etbackend.test_backend.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder encoder;

    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"admin".equals(username)) throw new UsernameNotFoundException("Usuario no encontrado");

        return User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
    }
}