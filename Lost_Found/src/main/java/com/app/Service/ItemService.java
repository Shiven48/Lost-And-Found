package com.app.Service;

import com.app.Entity.Item;
import com.app.Entity.UserFound;
import com.app.Entity.UserLost;
import com.app.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    ItemRepository objectRepository;
    UserFoundService userFoundService;
    // UserLostService userLostService;

    @Autowired
    public ItemService(ItemRepository objectRepository, UserFoundService userFoundService) {
        this.objectRepository = objectRepository;
        this.userFoundService = userFoundService;
    }

    public Object createItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }
        try {
            System.out.println(item);
            Item response_item = new Item();
            response_item.setName(item.getName());
            response_item.setDescription(item.getDescription());
            response_item.setType(item.getType());
            response_item.setObj_image(item.getObj_image());
            response_item.setPlace(item.getPlace());
            response_item.setTime(item.getTime());
            response_item.setLost_found(item.getLost_found());

            UserFound found = item.getUserfound();

            if (found != null) {
                response_item.setUserfound(userFoundService.getById(found.getUser_Id()));
            } else {
                item.setUserfound(null);
            }
            // Same conditions for the userLost
            return response_item;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create object", e);
        }
    }
}

