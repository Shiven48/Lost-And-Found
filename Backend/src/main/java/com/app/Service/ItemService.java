package com.app.Service;

import com.app.Models.DTO.Item.*;
import com.app.Models.Entities.Item;
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
        checkItemExists(id);
        Item item = fetchItemById(id);
        itemRepository.delete(item);
        return itemMapper.toItemDeleteResponseDto(item);
    }

    private boolean checkIfIdNull(Long id){
        return id!=null;
    }

    private void checkItemExists(Long id) {
        if(!itemRepository.existsById(id)){
            throw  new ResourceNotFoundException("Object with id: " + id + " does not exist");
        }
        return;
    }

    private Item fetchItemById(Long id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
    }

    private void CheckItemNull(Object obj){
        if(obj == null){
            throw new IllegalArgumentException("Object cannot be null");
        }
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
        if(!checkIfIdNull(id)) {
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
        try {
            CheckItemNull(itemRequestDto);
            Item item = new Item();
            itemMapper.ItemFromRequestDto(item,itemRequestDto);
            Item resp_item = itemRepository.save(item);

            return itemMapper.toItemResponseDto(resp_item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create object", e);
        }
    }

    @Transactional
    public <T>T createLostItem(ItemWithoutFounder itemWithoutFounder){
        try{
            CheckItemNull(itemWithoutFounder);
            Item item = new Item();
            itemMapper.ItemFromWithoutFounderDto(item,itemWithoutFounder);
            item.getOwner().addLostItem(item);
            return validate(itemRepository.save(item));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Lost object", e);
        }
    }

    @Transactional
    public <T>T createFoundItem(ItemWithoutOwner itemWithoutOwner){
        try{
            CheckItemNull(itemWithoutOwner);
            Item item = new Item();
            itemMapper.ItemFromWithoutOwnerDto(item,itemWithoutOwner);
            item.getFinder().addFoundItem(item);
            return validate(itemRepository.save(item));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Found object", e);
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

    @Transactional
    public ItemResponseDto updateItem(ItemRequestDto item, Long id) {
        if(!checkIfIdNull(id)){
            throw new IllegalArgumentException("Object id cannot be null");
        }
        try{
            CheckItemNull(item);
            checkItemExists(id);
            Item fetchedItem = fetchItemById(id);
            itemMapper.ItemFromRequestDto(fetchedItem,item);
            Item savedItem = itemRepository.save(fetchedItem);
            return itemMapper.toItemResponseDto(savedItem);
        } catch(Exception e){
            throw new RuntimeException("Failed to update object with id: "+id, e);
        }
    }

    // Used to call different dto based on the input at runtime (Not any real validation or security aspect)
    public <T>T validate(Item item) {
        if(item.getTags() != null) {
            if(item.getFinder() != null && item.getOwner() != null) {
                // dto with owner and finder
                return (T) itemMapper.toItemResponseDto(item);
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

    public <T> List<T> PaginateAndSort(int pages, int pageSize, String field, String direction) {
        paginationAndSorting.validatePaginateAndSort(pages, pageSize, field, direction);
        return itemRepository.findAll(PageRequest.of(pages, pageSize).withSort(Sort.by(Sort.Direction.valueOf(direction),field)))
                .stream()
                .map( pageItem -> ((T) validate(pageItem)))
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

    public <T> List<T> getByCategory(String category) {
        List<Item> retrieved_Items = itemRepository.findAllByCategory(category)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category: " + category));
        return retrieved_Items
                .stream()
                .map(item -> ((T) validate(item)))
                .toList();
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