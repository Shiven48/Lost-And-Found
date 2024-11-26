package com.app.Mapper;

import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.Item.ItemDetailsDto;
import com.app.DTO.Item.ItemDto;
import com.app.Entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public static ItemDeleteResponseDto toItemDeleteResponseDto(Item item) {
            return new ItemDeleteResponseDto(
                    item.getId(),
                    item.getName()
            );
        }

    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
          item.getId(),
          item.getName(),
          item.getDescription(),
          item.getCategory(),
          item.getObj_Image(),
          item.getLost_found(),
          item.getPlace(),
          item.getTags(),
          item.getTime(),
          item.getFinder().getUserId(),
          item.getOwner().getUserId()
        );
    }


    public static Item toItem(ItemDto itemDto) {
            Item item = new Item();
            item.setId(itemDto.id());
            item.setName(itemDto.name());
            item.setDescription(itemDto.description());
            item.setCategory(itemDto.category());
            item.setObj_Image(itemDto.objImage());
            return item;
    }

    public static ItemDetailsDto toItemDetail(ItemDto item) {
        return new ItemDetailsDto(
                item.id(),
                item.name(),
                item.lostFound(),
                item.tags(),
                item.place(),
                item.time(),
                item.finderId(),
                item.ownerId()
        );
    }
}
