package com.app.Service;

import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.Item.ItemDto;
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
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, UserService userService, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Item createItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        try {
            Item resp_item = new Item();

            resp_item.setName(item.getName());
            resp_item.setDescription(item.getDescription());
            resp_item.setCategory(item.getCategory());
            resp_item.setPlace(item.getPlace());
            resp_item.setTime(item.getTime());
            resp_item.setLost_found(item.getLost_found());
            resp_item.setObj_Image(item.getObj_Image());
            resp_item.setTags(item.getTags());

            return itemRepository.save(resp_item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create object", e);
        }
    }

    public List<Item> getAllitems() {
        try{
            List<Item> items = itemRepository.findAll();
            return items;
        } catch(Exception e){
            throw new RuntimeException("Failed to retrieve all items", e);
        }
    }


    public Item getById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Object id cannot be null");
        }
        try{
            return itemRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        } catch(Exception e) {
            throw new RuntimeException("Failed to retrieve object with id: "+id, e);
        }
    }

    // basic update and then mapper
    public ItemResponseDto updateItem(ItemDto item, Long id) {
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

            updateItemFromDto(retrieved_item,item);

            Item savedItem = itemRepository.save(retrieved_item);

            return convertToDetailsDto(savedItem);
        } catch(Exception e){
            throw new RuntimeException("Failed to update object with id: "+id, e);
        }
    }

    private void updateItemFromDto(Item item, ItemDto dto) {
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

    private ItemResponseDto convertToDetailsDto(Item item) {
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

//
//    public ItemResponseDto updateItemFromDto(Item item, ItemDto dto) {
//        List<String> tags = new ArrayList<>();
//        UserDto finder = null;
//        UserDto owner = null;
//
//        if (dto.tags() != null) {
//            tags = new ArrayList<>(dto.tags());
//        }
//
//        if (dto.finderId() != null) {
//            finder = userService.userById(dto.finderId());
//        }
//
//        if (dto.ownerId() != null) {
//            owner = userService.userById(dto.finderId());
//        }
//        return new ItemResponseDto(
//                dto.id(),
//                dto.name(),
//                dto.description(),
//                dto.category(),
//                dto.objImage(),
//                dto.lostFound(),
//                dto.place(),
//                tags,
//                dto.time(),
//                finder,
//                owner
//        );
//    }

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
            return ItemMapper.toItemDeleteResponseDto(deleted_item);
        } catch(Exception e){
            throw new RuntimeException("Failed to delete object with id: "+id, e);
        }
    }
}