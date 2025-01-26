package com.app.Entity.DTO.Item;

import com.app.Entity.Enums.Lost_Found;
import com.app.Entity.Interface.Taggable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.List;

public record ItemRequestDto(
        @Size(min=2,max = 20)
        @NotBlank(message = "The name should not be empty")
        String name,

        @Size(max = 3000,message = "The message should not be more than 2000 words")
        String description,

        String category,

        String obj_Image,

        @NotNull(message = "Lost/Found status is required")
        Lost_Found lostFound,

        String place,

        List<String> tags,

        LocalTime time,

        Long finderId,

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
