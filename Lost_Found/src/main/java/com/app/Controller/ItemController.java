package com.app.Controller;

import com.app.Entity.Item;
import com.app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ItemController {

    ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    //Endpoint to create a new object (could be a lost or found object).
    @PostMapping(name = "AddObject", path = "/objects/object",consumes = {"Application/xml","Application/json"})
    public ResponseEntity<Object> addObject(@RequestBody Item item){
        Object resp_Object = itemService.createItem(item);
        return ResponseEntity.ok(resp_Object);
    }


}

















//
//        19. **Get Object by ID**
//        - `GET /objects/{id}`
//        - Endpoint to fetch an object by its ID.
//
//        20. **Update Object**
//        - `PUT /objects/{id}`
//        - Endpoint to update an object (e.g., change its details or type).
//
//        21. **Delete Object**
//        - `DELETE /objects/{id}`
//        - Endpoint to delete an object by its ID.
//
//        22. **Get All Objects**
//        - `GET /objects`
//        - Endpoint to fetch a list of all objects (both lost and found).
//
//        23. **Get Found Objects (filter by type)**
//        - `GET /objects/found`
//        - Endpoint to fetch all "found" objects.
//
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
