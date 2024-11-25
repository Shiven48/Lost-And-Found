package com.app.Controller;

import com.app.Entity.Item;
import com.app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {
//
//    ItemService itemService;
//
//    @Autowired
//    public ItemController(ItemService itemService){
//        this.itemService = itemService;
//    }
//
//    // Endpoint to create a new object (could be a lost or found object).
//    @PostMapping(name = "AddObject", path = "/objects/object",consumes = {"Application/xml","Application/json"})
//    public ResponseEntity<Object> addItem(@RequestBody Item item){
//        Object resp_Object = itemService.createItem(item);
//        return ResponseEntity.ok(resp_Object);
//    }
//
//    // Endpoint to fetch an object by its ID.
//    @GetMapping(name="getItem",path="objects/object/{id}")
//    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id){
//        Item resp_item = itemService.getById(id);
//        return ResponseEntity.ok(resp_item);
//    }
//
//    // Endpoint to update an object (e.g., change its details or type).
//    @PutMapping(name = "updateItem",path = "objects/object/{id}")
//    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable("id") Long id){
//        Item updated_item = itemService.updateItem(item,id);
//        return ResponseEntity.ok(updated_item);
//    }
//    // Endpoint to delete an object by its ID.
//    @DeleteMapping(name = "deleteItem",path = "objects/object/{id}")
//    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id){
//        Item deleted_Item = itemService.deleteItem(id);
//        return ResponseEntity.ok(deleted_Item);
//    }
//
//    // Endpoint to fetch a list of all objects (both lost and found).
//    @GetMapping(name="getAllItems", path = "objects")
//    public ResponseEntity<List<Item>> getAllItems(){
//        List<Item> resp_items = itemService.getAllitems();
//        return ResponseEntity.ok(resp_items);
//    }

}
















//        23. **Get Found Objects (filter by type)**
//        - `GET /objects/found`
//        - Endpoint to fetch all "found" objects.

//24. **Get Lost Objects (filter by type)**
//        - `GET /objects/lost`
//        - Endpoint to fetch all "lost" objects.
//
//25. **Get Objects by UserFound**
//        - `GET /objects/found/{userFoundId}`
//        - Endpoint to fetch all objects associated with a specific `UserFound`.
//
//        26. **Get Objects by UserLost**
//        - `GET /objects/lost/{userLostId}`
//        - Endpoint to fetch all objects associated with a specific `UserLost`.
