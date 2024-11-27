package com.app.DTO.User;

import com.app.DTO.Credentials.CredentialsResponseDto;
import com.app.Mapper.CredentialMapper;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long userId,
        String name,
        Boolean isLoggedIn,
        LocalDateTime registrationDate,
        LocalDateTime lastModified,
        CredentialsResponseDto credentials
) {
}
