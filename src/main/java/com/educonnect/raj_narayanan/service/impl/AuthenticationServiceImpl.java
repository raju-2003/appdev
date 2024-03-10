package com.educonnect.raj_narayanan.service.impl;

import static com.educonnect.raj_narayanan.enumerated.Role.USER;
import static com.educonnect.raj_narayanan.enumerated.TokenType.BEARER;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.educonnect.raj_narayanan.dto.request.ForgotPasswordRequest;
import com.educonnect.raj_narayanan.dto.request.LoginRequest;
import com.educonnect.raj_narayanan.dto.request.RegisterRequest;
import com.educonnect.raj_narayanan.dto.request.ResetPasswordRequest;
import com.educonnect.raj_narayanan.dto.response.ForgotPasswordResponse;
import com.educonnect.raj_narayanan.dto.response.LoginResponse;
import com.educonnect.raj_narayanan.dto.response.RegisterResponse;
import com.educonnect.raj_narayanan.dto.response.ResetPasswordResponse;
import com.educonnect.raj_narayanan.model.Otp;
import com.educonnect.raj_narayanan.model.Token;
import com.educonnect.raj_narayanan.model.User;
import com.educonnect.raj_narayanan.repository.OtpRepository;
import com.educonnect.raj_narayanan.repository.TokenRepository;
import com.educonnect.raj_narayanan.repository.UserRepository;
import com.educonnect.raj_narayanan.service.AuthenticationService;
import com.educonnect.raj_narayanan.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtUtil jwtUtil;
        private final UserRepository userRepository;
        private final TokenRepository tokenRepository;
        private final JavaMailSender javaMailSender;
        private final OtpRepository otpRepository;

        


        @Override
        public RegisterResponse register(RegisterRequest request) {
                var user = User.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .phone(request.getPhone())
                                .role(USER)
                                .build();
                userRepository.save(user);
                return RegisterResponse.builder()
                                .message("User registered successfully")
                                .build();
        }

        @Override
        public LoginResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                request.getPassword()));
                var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", user.getRole().toString());
                var accessToken = jwtUtil.generateToken(claims, user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                System.out.println("login");
                return LoginResponse.builder()
                                .message("Logged in successfully")
                                .accessToken(accessToken)
                                .build();
        }

        private void saveUserToken(User user, String accessToken) {
                var token = Token.builder()
                                .user(user)
                                .token(accessToken)
                                .tokenType(BEARER)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepository.save(token);
        }

        private void revokeAllUserTokens(User user) {
                var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
                if (validUserTokens.isEmpty())
                        return;
                validUserTokens.forEach(token -> {
                        token.setExpired(true);
                        token.setRevoked(true);
                });
                tokenRepository.saveAll(validUserTokens);
        }

        @Override
        public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
                String otp = RandomStringUtils.randomNumeric(6);
                var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
                otpRepository.save(Otp.builder()
                                .email(request.getEmail())
                                .otp(otp)
                                .createdTime(LocalDateTime.now())
                                .expiryTime(LocalDateTime.now().plusMinutes(5))
                                .usedTime(null)
                                .isUsed(false)
                                .isExpired(false)
                                .build());

                if(user != null) {
                        SimpleMailMessage mailMessage = new SimpleMailMessage();
                        mailMessage.setTo(request.getEmail());
                        mailMessage.setSubject("Password Reset Request");
                        mailMessage.setText("Your OTP is: " + otp);
                        javaMailSender.send(mailMessage);
                        return ForgotPasswordResponse.builder()
                                        .message("OTP sent to your email "+request.getEmail())
                                        .build();
                }else{
                        return ForgotPasswordResponse.builder()
                                        .message("User not found")
                                        .build();
                }
                
                
        }

        @Override
        public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
                var otp = otpRepository.findByEmail(request.getEmail()).orElseThrow();
                if (otp.getOtp().equals(request.getOtp()) && !otp.isExpired() && !otp.isUsed()) {
                        if (request.getNewpassword().equals(request.getConfirmPassword())) {
                                var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
                                user.setPassword(passwordEncoder.encode(request.getNewpassword()));
                                userRepository.save(user);
                                otp.setUsedTime(LocalDateTime.now());
                                otp.setUsed(true);
                                otp.setExpired(true);
                                otpRepository.save(otp);
                                return ResetPasswordResponse.builder()
                                                .message("Password reset successfully")
                                                .build();
                        } else {
                                return ResetPasswordResponse.builder()
                                                .message("Password and confirm password does not match")
                                                .build();
                        }
                } else {
                        return ResetPasswordResponse.builder()
                                        .message("Invalid OTP")
                                        .build();
                }
                
        }
}