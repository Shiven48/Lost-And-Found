package com.app.Entity.DTO.User;

import com.app.Entity.Models.Item;

import java.util.List;

public record UserFoundItemsDto(
//        Long id,
        String name,
        Boolean isLoggedIn,
        List<Item> foundItems
) {
}
