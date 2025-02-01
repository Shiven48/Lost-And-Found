package com.app.Models.DTO.Admin;

public record AdminResponseDto(
        Long adminId,
        String name,
        Boolean isLoggedIn
) {
}
