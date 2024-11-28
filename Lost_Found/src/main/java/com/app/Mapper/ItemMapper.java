package com.app.Mapper;

import com.app.DTO.Item.*;
import com.app.Entity.Item;
import com.app.Entity.Lost_Found;
import com.app.Entity.User;
import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
                item.getTime()
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

        item.setTags(addTags(item,dto));

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

    public List<String> addTags(Item item, ItemRequestDto dto) {
        if(dto.tags() == null) {
            List<String> tags = new ArrayList<>();
            tags.add(dto.name());
            return new ArrayList<>(tags);
        } else {
            return new ArrayList<>(dto.tags());
        }
    }

    public List<String> addTags(Item item) {
        if(item.getTags() == null) {
            List<String> tags = new ArrayList<>();
            tags.add(item.getName());
            return new ArrayList<>(tags);
        } else {
            return new ArrayList<>(item.getTags());
        }
    }

    public ItemWithoutFounder toItemWithoutFounder(Item item) {
        return new ItemWithoutFounder(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getObj_Image(),
                item.getLost_found(),
                item.getPlace(),
                item.getTags(),
                item.getTime(),
                userMapper.toUserDto(item.getOwner())
        );
    }

    public ItemWithoutOwner toItemWithoutOwner(Item item){
        return new ItemWithoutOwner(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getObj_Image(),
                item.getLost_found(),
                item.getPlace(),
                item.getTags(),
                item.getTime(),
                userMapper.toUserDto(item.getFinder())
        );
    }

    public Object toFullItemResponseDto(Item item) {
        return new ItemFullResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getObj_Image(),
                item.getLost_found(),
                item.getPlace(),
                item.getTags(),
                item.getTime(),
                userMapper.toUserDto(item.getOwner()),
                userMapper.toUserDto(item.getFinder())
        );
    }
}
