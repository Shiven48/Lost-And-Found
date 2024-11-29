package com.app.DTO.User;

import com.app.DTO.Item.ItemResponseDto;
import com.app.Entity.Item;

import java.util.List;

public record UserLostItemsDto(
        Long id,
        String name,
        Boolean isLoggedIn,
        List<Item> lostItems
) {
}
