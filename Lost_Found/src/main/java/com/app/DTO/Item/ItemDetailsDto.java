package com.app.DTO.Item;

import com.app.Entity.Lost_Found;

import java.time.LocalTime;
import java.util.List;

public record ItemDetailsDto(
        Long id,
        String name,
        Lost_Found lostFound,
        List<String> tags,
        String place,
        LocalTime time,
        Long finderId,
        Long ownerId
) {
}
