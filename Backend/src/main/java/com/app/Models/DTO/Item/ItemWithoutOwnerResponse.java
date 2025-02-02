package com.app.Models.DTO.Item;

import com.app.Models.DTO.User.UserDto;
import com.app.Models.DTO.User.UserResponseDto;
import com.app.Models.Enums.Category;
import com.app.Models.Enums.Lost_Found;

import java.time.LocalTime;
import java.util.List;

public record ItemWithoutOwnerResponse(
        Long id,
        String name,
        String description,
        Category category,
        String objImage,
        Lost_Found lostFound,
        String place,
        List<String> tags,
        LocalTime time,
        UserResponseDto founder
) {
}
