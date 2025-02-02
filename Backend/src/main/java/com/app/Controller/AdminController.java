package com.app.Controller;

import com.app.Models.DTO.Admin.AdminResponseDto;
import com.app.Models.DTO.Item.ItemDeleteResponseDto;
import com.app.Models.DTO.User.UserResponseDto;
import com.app.Models.Entities.User;
import com.app.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admins")
@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // Endpoint to return all the Admins
    public ResponseEntity<List<AdminResponseDto>> getAdmins(){
        List<AdminResponseDto> admins = adminService.fetchAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // Endpoint to fetch all Users by Admin
//    @GetMapping(path="/users")
//    public ResponseEntity<List<UserResponseDto>> fetchAll(
//            @RequestParam(defaultValue = "0") int pages,
//            @RequestParam(defaultValue = "10") int pageSize,
//            @RequestParam(required = false) String sortField,
//            @RequestParam(required = false) String direction
//    ) {
//        List<UserResponseDto> users = adminService.userFoundAll(pages,pageSize,sortField,direction);
//        return ResponseEntity.ok(users);
//    }
    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> fetchAll() {
        List<User> users = adminService.userFoundAll();
        return ResponseEntity.ok(users);
    }

    // Endpoint to delete any user by id
    @DeleteMapping(path = "users/{id}")
    public ResponseEntity<UserResponseDto> deleteUserFound(@PathVariable("id") Long id) {
        try {
            UserResponseDto deletedUser = adminService.deleteUser(id);
            return ResponseEntity.ok(deletedUser);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete user by id : "+id, e);
        }
    }

    // Endpoint to delete any item by id
    @DeleteMapping(path = "items/{id}")
    public ResponseEntity<ItemDeleteResponseDto> deleteItem(@PathVariable("id") Long id){
        try{
            ItemDeleteResponseDto deletedItem = adminService.deleteItem(id);
            return ResponseEntity.ok(deletedItem);
        } catch(Exception e){
            throw new RuntimeException("Failed to delete item by id : "+id, e);
        }
    }
}
