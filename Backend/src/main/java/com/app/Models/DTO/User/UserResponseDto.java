package com.app.Models.DTO.User;

public record UserResponseDto(
        Long userId,
        String name,
        Boolean isLoggedIn
) {
}
