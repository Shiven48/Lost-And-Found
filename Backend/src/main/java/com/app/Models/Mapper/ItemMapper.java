package com.app.Models.Mapper;

import com.app.Models.DTO.Credentials.CredentialsResponseDto;
import com.app.Models.DTO.Item.*;
import com.app.Models.Entities.Credentials;
import com.app.Models.Entities.Item;
import com.app.Models.Enums.Lost_Found;
import com.app.Models.Entities.User;
import com.app.Models.Interface.UserType;
import com.app.Repository.CredentialsRepository;
import com.app.Service.CredentialService;
import com.app.Service.ItemService;
import com.app.Service.UserService;
import com.app.Utils.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Models.Interface.Taggable;
import com.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemMapper {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    ItemMapper(
            UserMapper userMapper,
            UserRepository userRepository
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public ItemDeleteResponseDto toItemDeleteResponseDto(Item item) {
            return new ItemDeleteResponseDto(
                    item.getId(),
                    item.getName()
            );
        }

    public void ItemFromWithoutOwnerDto(Item item, ItemWithoutOwner dto) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.objImage());

        // This mean I am a user and I have found the item
        item.setLost_found(Lost_Found.FOUND);

        item.setPlace(dto.place());
        item.setTags(addTags(dto));

        item.setTime(dto.time());

        if (dto.founderId() != null) {
            User finder = userRepository.findById(dto.founderId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + dto.founderId()));
            item.setFinder(finder);
        }
    }

    public void ItemFromWithoutFounderDto(Item item, ItemWithoutFounder dto) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.objImage());

        item.setLost_found(Lost_Found.LOST);

        item.setPlace(dto.place());
        item.setTags(addTags(dto));

        item.setTime(dto.time());

        if(dto.ownerId() != null) {
            User owner = userRepository.findById(dto.ownerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + dto.ownerId()));
            item.setOwner(owner);
        }
    }

    public void ItemFromRequestDto(Item item, ItemRequestDto dto) {
        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.obj_Image());
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
                userMapper.userToUserResponseDto(item.getOwner(),item.getOwner().getCredentials())
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
                userMapper.userToUserResponseDto(item.getFinder(),item.getFinder().getCredentials())
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
                userMapper.userToUserResponseDto(item.getOwner(),item.getOwner().getCredentials()),
                userMapper.userToUserResponseDto(item.getOwner(),item.getFinder().getCredentials())
        );
    }
}
