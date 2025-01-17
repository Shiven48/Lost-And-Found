package com.app.DTO.User;

import com.app.Entity.Item;

import java.util.List;

public record UserFoundItemsDto(
        Long id,
        String name,
        Boolean isLoggedIn,
        List<Item> foundItems
) {
}
