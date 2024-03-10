package com.educonnect.raj_narayanan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
    private String email;
    private String newpassword;
    private String confirmPassword;
    private String otp;
}
