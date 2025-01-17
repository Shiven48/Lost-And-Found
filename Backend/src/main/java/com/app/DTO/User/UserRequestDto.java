package com.app.DTO.User;

public record UserRequestDto(
        String name,
        Boolean isLoggedIn,
        Long credentialId
) {
}
