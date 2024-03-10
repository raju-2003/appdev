package com.educonnect.raj_narayanan.controller;

import static com.educonnect.raj_narayanan.utils.MyConstant.AUTH;
import static com.educonnect.raj_narayanan.utils.MyConstant.FORGOT_PASSWORD;
import static com.educonnect.raj_narayanan.utils.MyConstant.RESET_PASSWORD;
import static com.educonnect.raj_narayanan.utils.MyConstant.LOGIN;
import static com.educonnect.raj_narayanan.utils.MyConstant.REGISTER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educonnect.raj_narayanan.dto.request.ForgotPasswordRequest;
import com.educonnect.raj_narayanan.dto.request.ResetPasswordRequest;
import com.educonnect.raj_narayanan.dto.request.LoginRequest;
import com.educonnect.raj_narayanan.dto.request.RegisterRequest;
import com.educonnect.raj_narayanan.dto.response.ForgotPasswordResponse;
import com.educonnect.raj_narayanan.dto.response.ResetPasswordResponse;
import com.educonnect.raj_narayanan.dto.response.LoginResponse;
import com.educonnect.raj_narayanan.dto.response.RegisterResponse;
import com.educonnect.raj_narayanan.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(AUTH)
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    
    @PostMapping(REGISTER)
    @Operation(summary = "Register a new user")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        try {
            response = authService.register(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Registration failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PostMapping(LOGIN)
    @Operation(summary = "Login to the application")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            response = authService.login(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Login failed!");
            response.setAccessToken("");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PostMapping(FORGOT_PASSWORD)
    @Operation(summary = "Forgot password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        ForgotPasswordResponse response = new ForgotPasswordResponse();
        try {
            response = authService.forgotPassword(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PostMapping(RESET_PASSWORD)
    @Operation(summary = "Reset password")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        ResetPasswordResponse response = new ResetPasswordResponse();
        try {
            response = authService.resetPassword(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }


}
