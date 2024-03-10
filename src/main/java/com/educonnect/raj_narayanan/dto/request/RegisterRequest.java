package com.educonnect.raj_narayanan.dto.request;

import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private long phone;
}
