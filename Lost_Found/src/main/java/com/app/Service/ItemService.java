package com.app.Service;

import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.Item.ItemRequestDto;
import com.app.DTO.Item.ItemResponseDto;
import com.app.Entity.Item;
import com.app.Entity.User;
import com.app.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Mapper.ItemMapper;
import com.app.Mapper.UserMapper;
import com.app.Repository.ItemRepository;
import com.app.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings(value = "unchecked")
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                        ItemMapper itemMapper1
    ) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper1;
    }


    public <T> List<T> getAllitems() {
        try{
            List<Item> items = itemRepository.findAll();
            List<T> response_items = items.stream()
                    .map(item -> {
                        return (T) validate(item);
                    })
                    .toList();
            return response_items;
        } catch(Exception e){
            throw new RuntimeException("Failed to retrieve all items", e);
        }
    }

    public <T>T getById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Object id cannot be null");
        }
        try{
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            return validate(item);
        } catch(Exception e) {
            throw new RuntimeException("Failed to retrieve object with id: "+id, e);
        }
    }


    public ItemResponseDto createItem(ItemRequestDto itemRequestDto) {
        if (itemRequestDto == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        try {
            Item item = new Item();
            itemMapper.ItemFromRequestDto(item,itemRequestDto);
            Item resp_item = itemRepository.save(item);

            return itemMapper.toItemResponseDto(resp_item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create object", e);
        }
    }

    public ItemResponseDto updateItem(ItemRequestDto item, Long id) {
        if(id == null){
            throw new IllegalArgumentException("Object id cannot be null");
        }
        if(item == null){
            throw new IllegalArgumentException("Object cannot be null");
        }
        try{
            if(!itemRepository.existsById(id)){
                throw  new ResourceNotFoundException("Object with id: " + id + " does not exist");
            }
            Item retrieved_item = itemRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));

            itemMapper.ItemFromRequestDto(retrieved_item,item);

            Item savedItem = itemRepository.save(retrieved_item);

            return itemMapper.toItemResponseDto(savedItem);
        } catch(Exception e){
            throw new RuntimeException("Failed to update object with id: "+id, e);
        }
    }

    @Transactional
    public ItemDeleteResponseDto deleteItem(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Object id cannot be null");
        }
        try{
            if(!itemRepository.existsById(id)) {
                throw new ResourceNotFoundException("Object with id: " + id + " does not exist");
            }
            Item deleted_item = itemRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));

           String name = deleted_item.getName();

            itemRepository.delete(deleted_item);
            return itemMapper.toItemDeleteResponseDto(deleted_item);
        } catch(Exception e){
            throw new RuntimeException("Failed to delete object with id: "+id, e);
        }
    }

    public <T>T validate(Item item) {
        // One tag required compulsory
        item.setTags(itemMapper.addTags(item));
        if(item.getTags() != null) {
            if(item.getFinder() != null && item.getOwner() != null) {
                // dto with owner and finder
                return (T) itemMapper.toFullItemResponseDto(item);
            }
            if(item.getOwner() == null && item.getFinder() == null) {
                // dto without owner and finder
                return (T) itemMapper.toItemResponseDto(item);
            }
            if(item.getFinder() != null){
                //dto without owner
                return (T) itemMapper.toItemWithoutOwner(item);
            }
            if(item.getOwner() != null){
                //dto without finder
                return (T) itemMapper.toItemWithoutFounder(item);
            }
        }
        throw new IllegalArgumentException("Parameters are not set correctly");
    }

    public <T> List<T> getAllFoundItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().filter(
                    item -> "FOUND".equalsIgnoreCase(item.getLost_found().toString())
                    ).map(item -> {
                        return (T) validate(item);
                    }
        ).toList();
    }

    public <T> List<T> getAllLostItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().filter(
                item -> "LOST".equalsIgnoreCase(item.getLost_found().toString())
        ).map(item -> {
                    return (T) validate(item);
                }
        ).toList();
    }
}