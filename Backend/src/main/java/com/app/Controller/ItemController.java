package com.app.Controller;

import com.app.Models.DTO.Item.*;
import com.app.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Endpoint to fetch a list of all objects (both lost and found).
    @GetMapping(name = "getAllItems", path = "")
    public <T> ResponseEntity<List<T>> getAllItems() {
        List<T> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // Endpoint to fetch an object by its ID.
    @GetMapping(name = "getItem", path = "/{id}")
    public <T> ResponseEntity<T> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.getById(id));
    }

    // Endpoint to add CreateLostItem (I have lost the item) -> It would create new ID it means new item, and it means can update
    @PostMapping(name = "createLostItem", path = "lost")
    public <T> ResponseEntity<T> addLostItem(@RequestBody ItemWithoutFounder itemWithoutFounder) {
        T item_resp = itemService.createLostItem(itemWithoutFounder);
        return ResponseEntity.status(HttpStatus.CREATED).body(item_resp);
    }

    // Endpoint to add CreateFoundItem (I have founded the item) -> It would create new ID it means new item, and it means can update
    @PostMapping(name = "createFoundItem", path = "found")
    public <T> ResponseEntity<T> addFoundItem(@RequestBody ItemWithoutOwner itemWithoutOwner) {
        T item_resp = itemService.createFoundItem(itemWithoutOwner);
        return ResponseEntity.status(HttpStatus.CREATED).body(item_resp);
    }

    // Endpoint to get all Found Items
    @GetMapping(path = "/found")
    public <T> ResponseEntity<List<T>> getFoundItems() {
        List<T> allFetchedFoundItems = itemService.getAllFoundItems();
        return ResponseEntity.ok(allFetchedFoundItems);
    }

    // Endpoint to get all Lost Items
    @GetMapping(path = "/lost")
    public <T> ResponseEntity<List<T>> getLostItems() {
        List<T> allFetchedLostItems = itemService.getAllLostItems();
        return ResponseEntity.ok(allFetchedLostItems);
    }

    // Endpoint to update an object (e.g., change its details or type).
    @PutMapping(name = "updateItem", path = "{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@RequestBody ItemRequestDto itemDto, @PathVariable("id") Long id) {
        ItemResponseDto itemDetailsDto = itemService.updateItem(itemDto, id);
        return ResponseEntity.ok(itemDetailsDto);
    }


    // Endpoint to delete an object by its ID.
    @DeleteMapping(name = "deleteItem", path = "{id}")
    public ResponseEntity<ItemDeleteResponseDto> deleteItem(@PathVariable("id") Long id) {
        ItemDeleteResponseDto deleted_Item = itemService.deleteItem(id);
        return ResponseEntity.ok(deleted_Item);
    }

    // Endpoint to get Item based on the Category
    @GetMapping(path = "/category/{category}")
    public <T> ResponseEntity<List<T>> itemsByCategory(@PathVariable("category") String category) {
        List<T> allItemsCategory = itemService.getByCategory(category);
        return ResponseEntity.ok(allItemsCategory);
    }

    // Search Functionality based on category and name
    @PostMapping(path="/search")
    public <T> ResponseEntity<List<T>> itemsBySearch(
            @RequestBody SearchParamDto searchParamDto){

        List<T> items = itemService.searchByNameAndCategory(searchParamDto);
        return ResponseEntity.ok(items);
    }

    // Pagination and Sorting
    @GetMapping(path = "/paginationAndSorting")
    public <T> ResponseEntity<List<T>> paginationAndSorting(@RequestParam(defaultValue = "0") int pages,
                                                            @RequestParam(defaultValue = "10") int pageSize,
                                                            @RequestParam(required = false) String sortField,
                                                            @RequestParam(required = false) String direction

    ) {
        List<T> pageItemData = itemService.PaginateAndSort(pages, pageSize, sortField, direction);
        return ResponseEntity.ok(pageItemData);
    }
}
