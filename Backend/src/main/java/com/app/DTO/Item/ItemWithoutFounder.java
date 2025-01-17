package com.app.DTO.Item;

import com.app.DTO.User.UserDto;
import com.app.Entity.Lost_Found;
import com.app.Interface.Taggable;

import java.time.LocalTime;
import java.util.List;

public record ItemWithoutFounder(
        String name,
        String description,
        String category,
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
