package com.app.Entity.DTO.Item;

import com.app.Entity.DTO.User.UserDto;
import com.app.Entity.Enums.Lost_Found;

import java.time.LocalTime;
import java.util.List;

public record ItemWithoutFounderResponse(
        Long id,
        String name,
        String description,
        String category,
        String objImage,
        Lost_Found lostFound,
        String place,
        List<String> tags,
        LocalTime time,
        UserDto owner
) {
}
