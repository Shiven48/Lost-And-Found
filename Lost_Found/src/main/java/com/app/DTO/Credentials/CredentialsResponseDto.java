package com.app.DTO.Credentials;

public record CredentialsResponseDto(
        Long id,
        String email,
        String password
) {
}
