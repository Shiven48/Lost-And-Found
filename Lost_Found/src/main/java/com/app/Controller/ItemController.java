package com.app.Controller;

import com.app.DTO.Item.*;
import com.app.DTO.User.UserDto;
import com.app.Entity.Lost_Found;
import com.app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }


    // Endpoint to fetch a list of all objects (both lost and found).
    @GetMapping(name="getAllItems", path = "/items")
    public <T> ResponseEntity<List<T>> getAllItems(){
        List<T> items = (List<T>) itemService.getAllitems();
        return ResponseEntity.ok(items);
    }

    // Endpoint to fetch an object by its ID.
    @GetMapping(name="getItem",path="/items/{id}")
    public ResponseEntity getItemById(@PathVariable("id") Long id){
        return ResponseEntity.ok(itemService.getById(id));
    }

    // Endpoint to add CreateLostItem
    @PostMapping(name="createLostItem",path = "items/lost")
    public <T> ResponseEntity<T> addLostItem(@RequestBody ItemWithoutOwner itemWithoutOwner){
        T item_resp = itemService.createLostItem(itemWithoutOwner);
        return ResponseEntity.ok(item_resp);
    }

    // Endpoint to add CreateFoundItem
    @PostMapping(name="createFoundItem",path = "items/found")
    public <T> ResponseEntity<T> addFoundItem(@RequestBody ItemWithoutFounder itemWithoutFounder){
        T item_resp = itemService.creatFoundItem(itemWithoutFounder);
        return ResponseEntity.ok(item_resp);
    }

    // Endpoint to update an object (e.g., change its details or type).
    @PutMapping(name = "updateItem",path = "items/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@RequestBody ItemRequestDto itemDto, @PathVariable("id") Long id){
       ItemResponseDto itemDetailsDto = itemService.updateItem(itemDto,id);
       return ResponseEntity.ok(itemDetailsDto);
    }

    // Endpoint to delete an object by its ID.
    @DeleteMapping(name = "deleteItem",path = "items/{id}")
    public ResponseEntity<ItemDeleteResponseDto> deleteItem(@PathVariable("id") Long id){
        ItemDeleteResponseDto deleted_Item = itemService.deleteItem(id);
        return ResponseEntity.ok(deleted_Item);
    }

    // Endpoint to get Found Items
    @GetMapping(path = "items/found")
    public <T> ResponseEntity<List<T>> getFoundItems(){
        List<T> allFetchedFoundItems = itemService.getAllFoundItems();
        return ResponseEntity.ok(allFetchedFoundItems);
    }

    // Endpoint to get Lost Items
    @GetMapping(path = "items/lost")
    public <T> ResponseEntity<List<T>> getLostItems(){
        List<T> allFetchedLostItems = itemService.getAllLostItems();
        return ResponseEntity.ok(allFetchedLostItems);
    }

    // Endpoint to get Items(Lost or Found) based on the Timestamp ascending
    @GetMapping(path = "items/{lost_found}/asc")
    public <T> ResponseEntity<List<T>> getItemTypeByTimeStampAsc(@PathVariable("lost_found") String lost_found){
        List<T> allAscTime = itemService.getTimeAsc(lost_found);
        return ResponseEntity.ok(allAscTime);
    }

    // Endpoint to get Items(Lost or Found) based on the Timestamp descending
    @GetMapping(path = "items/{lost_found}/desc")
    public <T> ResponseEntity<List<T>> getItemTypeByTimeStampDesc(@PathVariable("lost_found") String lost_found){
        List<T> allDescTime = itemService.getTimeDesc(lost_found);
        return ResponseEntity.ok(allDescTime);
    }

    // Endpoint to get Items based on the Timestamp ascending
    // Endpoint to get Items based on the Timestamp ascending
    // Endpoint to get LostItems based on the TimeStamp descending
    // Endpoint to get LostItem based on the Category
    // Endpoint to get LostItem based
    // Search Functionality based on name
}
