package com.app.Models.DTO.Item;

import com.app.Models.Enums.Category;
import com.app.Models.Interface.Taggable;

import java.time.LocalTime;
import java.util.List;

public record ItemWithoutFounder(
        String name,
        String description,
        Category category,
        String objImage,
        String place,
        List<String> tags,
        LocalTime time,
        Long ownerId
)implements Taggable {

    @Override
    public List<String> getTags(){
        return tags;
    }

    public String getName(){
        return name;
    }
}
