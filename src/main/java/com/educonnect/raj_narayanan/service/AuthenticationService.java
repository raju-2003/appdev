package com.educonnect.raj_narayanan.service;

import com.educonnect.raj_narayanan.dto.request.ForgotPasswordRequest;
import com.educonnect.raj_narayanan.dto.request.LoginRequest;
import com.educonnect.raj_narayanan.dto.request.RegisterRequest;
import com.educonnect.raj_narayanan.dto.request.ResetPasswordRequest;
import com.educonnect.raj_narayanan.dto.response.ForgotPasswordResponse;
import com.educonnect.raj_narayanan.dto.response.LoginResponse;
import com.educonnect.raj_narayanan.dto.response.RegisterResponse;
import com.educonnect.raj_narayanan.dto.response.ResetPasswordResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);

    ResetPasswordResponse resetPassword(ResetPasswordRequest request);


}