package com.app.Mapper;

import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.Item.ItemRequestDto;
import com.app.DTO.Item.ItemResponseDto;
import com.app.Entity.Item;
import com.app.Entity.User;
import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ItemMapper {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    ItemMapper(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public ItemDeleteResponseDto toItemDeleteResponseDto(Item item) {
            return new ItemDeleteResponseDto(
                    item.getId(),
                    item.getName()
            );
        }

    public ItemResponseDto toItemResponseDto(Item item) {
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
                userMapper.toUserDto(item.getFinder()),
                userMapper.toUserDto(item.getOwner())
        );
    }

    public void ItemFromRequestDto(Item item, ItemRequestDto dto) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.objImage());
        item.setLost_found(dto.lostFound());
        item.setPlace(dto.place());
        item.setTime(dto.time());

        if (dto.tags() != null) {
            item.setTags(new ArrayList<>(dto.tags()));
        }

        if (dto.finderId() != null) {
            User finder = userRepository.findById(dto.finderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Finder not found with id: " + dto.finderId()));
            item.setFinder(finder);
        }

        if (dto.ownerId() != null) {
            User owner = userRepository.findById(dto.ownerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + dto.ownerId()));
            item.setOwner(owner);
        }
    }

}
