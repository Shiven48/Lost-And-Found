package com.app.Entity.DTO.User;

import com.app.Entity.DTO.Credentials.CredentialsResponseDto;

public record UserResponseDto(
//        Long userId,
        String name,
        Boolean isLoggedIn,
//        LocalDateTime registrationDate,
//        LocalDateTime lastModified,
        CredentialsResponseDto credentials
) {
}
