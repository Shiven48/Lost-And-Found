package com.app.DTO.Item;

import com.app.Entity.Lost_Found;
import com.app.Interface.BaseDtoInterface;

import java.time.LocalTime;
import java.util.List;

public record ItemFoundRequestDto(
        String name,
        String description,
        String category,
        String objImage,
        Lost_Found lostFound,
        String place,
        List<String> tags,
        LocalTime time,
        Long finderId
) implements BaseDtoInterface {
}
