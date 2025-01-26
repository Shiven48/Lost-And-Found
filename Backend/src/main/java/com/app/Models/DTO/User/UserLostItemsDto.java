package com.app.Entity.DTO.User;

import com.app.Entity.Models.Item;

import java.util.List;

public record UserLostItemsDto(
//        Long id,
        String name,
        Boolean isLoggedIn,
        List<Item> lostItems
) {
}
