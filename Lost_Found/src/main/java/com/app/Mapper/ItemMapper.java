package com.app.Mapper;

import com.app.DTO.Item.*;
import com.app.Entity.Item;
import com.app.Entity.Lost_Found;
import com.app.Entity.User;
import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Interface.BaseDtoInterface;
import com.app.Repository.ItemRepository;
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
    private final ItemRepository itemRepository;
    private final  dto;

    @Autowired
    ItemMapper(UserMapper userMapper, UserRepository userRepository, ItemRepository itemRepository,T dto) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
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

    public <T extends BaseDtoInterface> ItemFromRequestDto(Item item, T dto) {
        String lostFound = dto.lostFound().toString().toUpperCase();

        item.setName(dto.name());
        item.setDescription(dto.description());
        item.setCategory(dto.category());
        item.setObj_Image(dto.objImage());
        item.setPlace(dto.place());
        item.setTime(dto.time());
        item.setTags(addTags(item,dto));
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

    public void ItemLostFromRequestDto(Item item, ItemLostRequestDto itemLostRequestDto) {
        item.setLost_found(itemLostRequestDto.lostFound()); // always lost

        ItemFromRequestDto(item,itemLostRequestDto); // return item
        Long id = itemLostRequestDto.ownerId();
        User owner = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No owner found with id : "+id));
        item.setOwner(owner);

    }
}
