package com.app.Models.DTO.Credentials;

public record CredentialsResponseDto(
        Long id,
        String name,
        String email,
        String role
) {
}
