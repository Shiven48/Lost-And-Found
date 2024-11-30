package com.app.Controller;

import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.Item.ItemRequestDto;
import com.app.DTO.Item.ItemResponseDto;
import com.app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // Endpoint to create a new object (could be a lost or found object).
    @PostMapping(name = "AddObject", path = "/items",consumes = {"Application/xml","Application/json"})
    public ResponseEntity<ItemResponseDto> addItem(@RequestBody ItemRequestDto item){
        ItemResponseDto resp_item = itemService.createItem(item);
        return ResponseEntity.ok(resp_item);
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

    // Endpoint to get Found Objects
    @GetMapping(path = "items/found")
    public <T> ResponseEntity<List<T>> getFoundItems(){
        List<T> allFetchedFoundItems = itemService.getAllFoundItems();
        return ResponseEntity.ok(allFetchedFoundItems);
    }


//24. **Get Lost Objects (filter by type)**
//        - `GET /objects/lost`
//        - Endpoint to fetch all "lost" objects.

}














//25. **Get Objects by UserFound**
//        - `GET /objects/found/{userFoundId}`
//        - Endpoint to fetch all objects associated with a specific `UserFound`.
//
//        26. **Get Objects by UserLost**
//        - `GET /objects/lost/{userLostId}`
//        - Endpoint to fetch all objects associated with a specific `UserLost`.
