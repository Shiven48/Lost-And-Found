package com.app.Service;

import com.app.Models.DTO.Item.*;
import com.app.Models.Entities.Item;
import com.app.Models.Enums.Lost_Found;
import com.app.Utils.Exception.ExceptionTypes.ResourceNotFoundException;
import com.app.Models.Mapper.ItemMapper;
import com.app.Repository.ItemRepository;
import com.app.Repository.Specification.ItemSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings(value = "unchecked")
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final PaginationAndSorting paginationAndSorting;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                       ItemMapper itemMapper1,
                       PaginationAndSorting paginationAndSorting) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper1;
        this.paginationAndSorting = paginationAndSorting;
    }

    @Transactional
    public ItemDeleteResponseDto deleteItem(Long id) {
        if(!checkIfIdNull(id)) {
            throw new IllegalArgumentException("Object id cannot be null");
        }
        if(!checkItemExists(id)){
            throw new ResourceNotFoundException("Object with id: " + id + " does not exist");
        }
        Item item = fetchItemById(id);
        itemRepository.delete(item);
        return itemMapper.toItemDeleteResponseDto(item);
    }

    private boolean checkIfIdNull(Long id){
        return id!=null;
    }

    private boolean checkItemExists(Long id) {
        return itemRepository.existsById(id);
    }

    private Item fetchItemById(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
    }


    public <T> List<T> getAllItems() {
        try{
            return itemRepository.findAll().stream()
                    .map(item -> {
                        return (T) validate(item);
                    }).toList();
        } catch(Exception e){
            throw new RuntimeException("Failed to retrieve all items", e);
        }
    }

    public <T>T getById(Long id) {
        if(checkIfIdNull(id)) {
            throw new IllegalArgumentException("Object id cannot be null");
        }
        try{
            Item item = fetchItemById(id);
            return validate(item);
        } catch(Exception e) {
            throw new RuntimeException("Failed to retrieve object with id: "+id, e);
        }
    }

    // Service to create Item (Deprecated)
    @Deprecated
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

    @Transactional
    public <T>T createLostItem(ItemWithoutOwner itemWithoutOwner){
        if(itemWithoutOwner == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        try{
            Item item = new Item();
            itemMapper.ItemFromWithoutOwnerDto(item,itemWithoutOwner);
            item.getFinder().addFoundItem(item);
            Item resp_item = itemRepository.save(item);
            return validate(resp_item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Lost object", e);
        }
    }

    @Transactional
    public <T>T creatFoundItem(ItemWithoutFounder itemWithoutFounder){
        if(itemWithoutFounder == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        try{
            Item item = new Item();
            itemMapper.ItemFromWithoutFounderDto(item,itemWithoutFounder);
            item.getOwner().addLostItem(item);
            Item resp_item = itemRepository.save(item);
            return validate(resp_item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Found object", e);
        }
    }

    // ================ X ================ X ================ X ================ X ================ X ================

    @Transactional
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

    public <T> List<T> getTimeAsc(String lost_found) {
        Lost_Found status = Lost_Found.valueOf(lost_found.toUpperCase());
        return itemRepository.findAllByLost_FoundOrderByTimeAsc(status)
                .stream()
                .map(item -> ((T) validate(item)))
                .toList();
    }

    public <T> List<T> getTimeDesc(String lost_found) {
        Lost_Found status = Lost_Found.valueOf(lost_found.toUpperCase());
        return itemRepository.findAllByLost_FoundOrderByTimeDesc(status)
                .stream()
                .map(item -> ((T) validate(item)))
                .toList();
    }

    public <T> List<T> getAllTimeAsc() {
        return itemRepository.findAllOrderByTimeAsc()
                .stream()
                .map(item -> ((T) validate(item)))
                .toList();
    }

    public <T> List<T> getAllTimeDesc() {
        return itemRepository.findAllOrderByTimeDesc()
                .stream()
                .map(item -> ((T) validate(item)))
                .toList();
    }

    public <T> List<T> getByCategory(String category) {
        List<Item> retrieved_Items = itemRepository.findAllByCategory(category)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category: " + category));
        return retrieved_Items
                .stream()
                .map(item -> ((T) validate(item)))
                .toList();
    }

    public <T> List<T> searchByNameAndCategory(SearchParamDto searchParamDto) {
        Specification<Item> spec = Specification.where(ItemSpecification.hasCategory(searchParamDto.category()))
                .and(ItemSpecification.hasName(searchParamDto.name()));

        return itemRepository
                .findAll(spec)
                .stream()
                .map((item) -> ((T) validate(item)))
                .toList();
    }

    public <T> List<T> PaginateAndSort(int pages, int pageSize, String field, String direction) {
        paginationAndSorting.validatePaginateAndSort(pages, pageSize, field, direction);
        return itemRepository.findAll(PageRequest.of(pages, pageSize).withSort(Sort.by(Sort.Direction.valueOf(direction),field)))
                .stream()
                .map( pageItem -> ((T) validate(pageItem)))
                .toList();
    }

    // Used to call different dto based on the input at runtime (Not any real validation or security aspect)
    public <T>T validate(Item item) {
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
                return (T) itemMapper.toItemWithoutOwnerResponse(item);
            }
            if(item.getOwner() != null){
                //dto without finder
                return (T) itemMapper.toItemWithoutFounderResponse(item);
            }
        }
        throw new IllegalArgumentException("Parameters are not set correctly");
    }

//    public void addTag(String tag) {
//        if (tag != null && !tag.trim().isEmpty()) {
//            this.tags.add(tag);
//        }
//    }
//
//    public void removeTag(String tag) {
//        this.tags.remove(tag);
//    }
}