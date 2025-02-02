package com.app.Models.DTO.Item;

import com.app.Models.DTO.User.UserResponseDto;

public record AddedResponseDto(
        Long id,
        UserResponseDto ownerId,
        UserResponseDto founderId
) {
}
