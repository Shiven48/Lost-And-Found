package com.app.Controller;

import com.app.DTO.Admin.AdminResponseDto;
import com.app.DTO.Item.ItemDeleteResponseDto;
import com.app.DTO.User.UserResponseDto;
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
    @GetMapping(path="/users")
    public ResponseEntity<List<UserResponseDto>> fetchAll() {
        try{

        } catch(Exception e){
            throw new RuntimeException("Failed to delete user found", e);
        }
        List<UserResponseDto> users = adminService.userFoundAll();
        return ResponseEntity.ok(users);
    }

    // Endpoint to delete any user by id
    @DeleteMapping(path = "admin/users/{id}")
    public ResponseEntity<UserResponseDto> deleteUserFound(@PathVariable("id") Long id) {
        try {
            UserResponseDto deletedUser = adminService.deleteUser(id);
            return ResponseEntity.ok(deletedUser);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete user by id : "+id, e);
        }
    }

    // Endpoint to delete any item by id
    public ResponseEntity<ItemDeleteResponseDto> deleteItem(Long id){
        try{
            ItemDeleteResponseDto deletedItem = adminService.deleteItem(id);
            return ResponseEntity.ok(deletedItem);
        } catch(Exception e){
            throw new RuntimeException("Failed to delete item by id : "+id, e);
        }
    }

    

}
