package com.app.Mapper;

import com.app.DTO.Item.*;
import com.app.DTO.User.UserDto;
import com.app.Entity.Item;
import com.app.Entity.Lost_Found;
import com.app.Entity.User;
import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Interface.Taggable;
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

    public void ItemFromWithoutOwnerDto(Item item, ItemWithoutOwner dto) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.objImage());

        item.setLost_found(Lost_Found.LOST);

        item.setPlace(dto.place());
        item.setTags(addTags(dto));

        item.setTime(dto.time());

        if (dto.founderId() != null) {
            User finder = userRepository.findById(dto.founderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Finder not found with id: " + dto.founderId()));
            item.setFinder(finder);
        }
    }

    public void ItemFromWithoutFounderDto(Item item, ItemWithoutFounder itemWithoutFounder) {
        item.setName(itemWithoutFounder.name());
        item.setDescription(itemWithoutFounder.description());
        item.setCategory(itemWithoutFounder.category());
        item.setObj_Image(itemWithoutFounder.objImage());

        item.setLost_found(Lost_Found.FOUND);

        item.setPlace(itemWithoutFounder.place());
        item.setTags(addTags(itemWithoutFounder));

        item.setTime(itemWithoutFounder.time());

        if(itemWithoutFounder.ownerId() != null) {
            User owner = userRepository.findById(itemWithoutFounder.ownerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + itemWithoutFounder.ownerId()));
            item.setOwner(owner);
        }
    }

    public void ItemFromRequestDto(Item item, ItemRequestDto dto) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.objImage());
        item.setLost_found(dto.lostFound());
        item.setPlace(dto.place());
        item.setTime(dto.time());

        item.setTags(addTags(dto));

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

    public <T extends Taggable> List<String> addTags(T dto) {
        if(dto.getTags() == null) {
            List<String> tags = new ArrayList<>();
            tags.add(dto.getName());
            return new ArrayList<>(tags);
        } else {
            return new ArrayList<>(dto.getTags());
        }
    }

    public ItemWithoutFounderResponse toItemWithoutFounderResponse(Item item) {
        return new ItemWithoutFounderResponse(
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

    public ItemWithoutOwnerResponse toItemWithoutOwnerResponse(Item item){
        return new ItemWithoutOwnerResponse(
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
