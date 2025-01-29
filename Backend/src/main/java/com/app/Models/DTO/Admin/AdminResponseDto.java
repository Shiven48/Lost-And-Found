package com.app.Models.DTO.Admin;

public record AdminResponseDto(
        Long userId,
        String name,
        Boolean isLoggedIn
) {
}
