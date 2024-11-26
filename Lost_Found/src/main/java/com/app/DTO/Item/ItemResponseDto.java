package com.app.DTO.Item;

import com.app.DTO.User.UserDto;
import com.app.Entity.Lost_Found;

import java.time.LocalTime;
import java.util.List;

public record ItemResponseDto(
        Long id,
        String name,
        String description,
        String category,
        String objImage,
        Lost_Found lostFound,
        String place,
        List<String> tags,
        LocalTime time,
        UserDto finder,
        UserDto owner
) {}
