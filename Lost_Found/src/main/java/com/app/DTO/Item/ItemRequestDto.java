package com.app.DTO.Item;

import com.app.Entity.Lost_Found;

import java.time.LocalTime;
import java.util.List;

public record ItemRequestDto(
        String name,
        String description,
        String category,
        String objImage,
        Lost_Found lostFound,
        String place,
        List<String> tags,
        LocalTime time,
        Long finderId,
        Long ownerId
) {
}
