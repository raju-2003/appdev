package com.educonnect.raj_narayanan.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.educonnect.raj_narayanan.enumerated.Role;
import com.educonnect.raj_narayanan.model.User;
import com.educonnect.raj_narayanan.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserClI implements CommandLineRunner{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() > 0)
            return;
        var user = User.builder().name("Admin")
                                .email("admin@gmail.com")
                                .password(passwordEncoder.encode("Admin@123"))
                                .phone(1234567890L)
                                .role(Role.ADMIN)
                                .build();
                userRepository.save(user);
    }
    
}
