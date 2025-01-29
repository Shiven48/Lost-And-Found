//package com.app.Models.DTO.Credentials;
//
//import jakarta.validation.constraints.*;
//
//public record CredentialsRequestDto(
//        @Email(message = "Please provide a valid email address",
//        regexp = "^(?=(.*[A-Z]))(?=(.*\\d))(?=(.*[\\W_]))[A-Za-z\\d\\W_]{8,25}@gmail\\.com$\n")
//        @NotBlank(message = "Email is required")
//        String email,
//
//        @NotBlank(message = "Password is required")
//        @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
//        String password,
//
//        @Pattern(regexp = "^ROLE_[A-Z]+$",message = "Role must start with 'ROLE_' followed by uppercase letters.")
//        String roles,
//
//        @NotBlank(message = "Name is required")
//        @Size(min=2, max = 25, message = "Name must be less than 25 characters")
//        String name
//) { }
