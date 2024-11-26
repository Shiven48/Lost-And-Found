package com.app.Mapper;

import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.Item.ItemDto;
import com.app.DTO.Item.ItemResponseDto;
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

    public static ItemResponseDto toItemResponseDto(Item item) {
        return new ItemResponseDto(
          item.getId(),
          item.getName(),
          item.getDescription(),
          item.getCategory(),
          item.getObj_Image(),
          item.getLost_found(),
          item.getPlace(),
          item.getTags(),
          item.getTime(),
          UserMapper.toUserDto(item.getFinder()),
          UserMapper.toUserDto(item.getOwner())
        );
    }


    public static ItemDto toItemDto(ItemResponseDto itemResponseDto) {
            return new ItemDto(
            itemResponseDto.id(),
            itemResponseDto.name(),
            itemResponseDto.description(),
            itemResponseDto.category(),
            itemResponseDto.objImage(),
            itemResponseDto.lostFound(),
            itemResponseDto.place(),
            itemResponseDto.tags(),
            itemResponseDto.time(),
            itemResponseDto.finder().id(),
            itemResponseDto.owner().id()
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

}
