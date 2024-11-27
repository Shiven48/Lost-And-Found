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

// Error
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                        ItemMapper itemMapper
    ) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    // Can have error for the tags,founder and Owner
    public List<ItemResponseDto> getAllitems() {
        try{
            List<Item> items = itemRepository.findAll();
            List<ItemResponseDto> response_items = items.stream()
                    .map(item -> {
                        validate(item);
                        return itemMapper.toItemResponseDto(item);
                    })
                    .toList();
            return response_items;
        } catch(Exception e){
            throw new RuntimeException("Failed to retrieve all items", e);
        }
    }

    // Can have error for the tags,founder and Owner
    public ItemResponseDto getById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Object id cannot be null");
        }
        try{
            Item item = itemRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            validate(item);
            return itemMapper.toItemResponseDto(item);
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

    private void validate(Item item) {
        // One tag required compulsory
        if(item.getTags() != null) {
            if(item.getOwner() == null && item.getFinder() == null) {
                // only tags
            }
            if(item.getFinder() != null){
                //dto without owner
            }
            if(item.getOwner() != null){
                //dto without finder
            }
        }
    }
}