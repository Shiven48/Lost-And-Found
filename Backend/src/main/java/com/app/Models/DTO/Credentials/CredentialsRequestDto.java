package com.app.Models.DTO.Credentials;

import jakarta.validation.constraints.*;

public record CredentialsRequestDto(

        @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
        String password,

        @Pattern(regexp = "^ROLE_[A-Z]+$",message = "Role must start with 'ROLE_' followed by uppercase letters.")
        String roles,

        @Size(min=2, max = 25, message = "Name must be less than 25 characters")
        String name

) { }
