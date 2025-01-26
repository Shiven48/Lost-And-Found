package com.app.Entity.DTO.Item;

import com.app.Entity.Interface.Taggable;

import java.time.LocalTime;
import java.util.List;

public record ItemWithoutOwner(
        String name,
        String description,
        String category,
        String objImage,
        String place,
        List<String> tags,
        LocalTime time,
        Long founderId
)implements Taggable {
    @Override
    public List<String> getTags(){
        return tags;
    }

    public String getName(){
        return name;
    }
}
