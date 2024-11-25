package com.app.Service;

import com.app.Entity.Item;
import com.app.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
//
//    private final ItemRepository itemRepository;
//    ItemRepository objectRepository;
//    UserFoundService userFoundService;
//    // UserLostService userLostService;
//
//    @Autowired
//    public ItemService(ItemRepository objectRepository, UserFoundService userFoundService, ItemRepository itemRepository) {
//        this.objectRepository = objectRepository;
//        this.userFoundService = userFoundService;
//        this.itemRepository = itemRepository;
//    }
//
//    public Object createItem(Item item) {
//        if (item == null) {
//            throw new IllegalArgumentException("Object cannot be null");
//        }
//        try {
//            System.out.println(item);
//            Item response_item = new Item();
//            response_item.setName(item.getName());
//            response_item.setDescription(item.getDescription());
//            response_item.setType(item.getType());
//            response_item.setObj_image(item.getObj_image());
//            response_item.setPlace(item.getPlace());
//            response_item.setTime(item.getTime());
//            response_item.setLost_found(item.getLost_found());
//
//            UserFound found = item.getUserfound();
//
//            if (found != null) {
//                response_item.setUserfound(userFoundService.getById(found.getUser_Id()));
//            } else {
//                item.setUserfound(null);
//            }
//            // Same conditions for the userLost
//            return itemRepository.save(response_item);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to create object", e);
//        }
//    }
//
//    public Item getById(Long id) {
//        if(id == null) {
//            throw new IllegalArgumentException("Object id cannot be null");
//        }
//        try{
//            return itemRepository.findById(id)
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
//        } catch(Exception e) {
//            throw new RuntimeException("Failed to retrieve object with id: "+id, e);
//        }
//    }
//
//    public Item updateItem(Item item, Long id) {
//        if(item == null) {
//            throw new IllegalArgumentException("Object cannot be null");
//        }
//        try{
//            if(id == null) {
//                throw new IllegalArgumentException("Object id cannot be null");
//            }
//            Item updated_Item = getById(id);
//            updated_Item.setName(item.getName());
//            updated_Item.setTime(item.getTime());
//            updated_Item.setPlace(item.getPlace());
//            updated_Item.setObj_image(item.getObj_image());
//            updated_Item.setLost_found(item.getLost_found());
//            updated_Item.setDescription(item.getDescription());
//            updated_Item.setType(item.getType());
//
//            //Get the id of the userlost and userfound
//            Long userfound_id = item.getUserfound().getUser_Id();
//            Long userlost_id = item.getUserlost().getUser_Id();
//
//            if(userfound_id != null && userlost_id != null) {
//                updated_Item.setUserfound(userFoundService.getById(userfound_id));
//            // updated_Item.setUserlost(userLostService.getById(userlost_id));
//            }
//            if(userfound_id == null){
//                updated_Item.setUserfound(null);
//            }
//            if(userlost_id == null){
//                updated_Item.setUserlost(null);
//            }
//            return updated_Item;
//        }catch(Exception e) {
//            throw new RuntimeException("Failed to update item with id "+id,e);
//        }
//    }
//
//    public Item deleteItem(Long id) {
//        if(id == null) {
//            throw new IllegalArgumentException("Object id cannot be null");
//        }
//        try{
//            Item resp_item = getById(id);
//            itemRepository.delete(resp_item);
//            return resp_item;
//        } catch(Exception e){
//            throw new RuntimeException("Failed to delete object with id: "+id, e);
//        }
//    }
//
//    public List<Item> getAllitems() {
//        try{
//            List<Item> resp_AllItems = itemRepository.findAll();
//            return resp_AllItems;
//        } catch(Exception e){
//            throw new RuntimeException("Failed to retrieve all items", e);
//        }
//    }
}

