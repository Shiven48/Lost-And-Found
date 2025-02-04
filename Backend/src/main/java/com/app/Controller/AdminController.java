package com.app.Controller;

import com.app.Models.DTO.Admin.AdminResponseDto;
import com.app.Models.DTO.Item.ItemDeleteResponseDto;
import com.app.Models.DTO.User.UserResponseDto;
import com.app.Models.Entities.User;
import com.app.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

//     Endpoint to fetch all Users by Admin
    @GetMapping(path="/users")
    public ResponseEntity<List<UserResponseDto>> fetchAll(
            @RequestParam(defaultValue = "0") int pages,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        List<UserResponseDto> users = adminService.userFoundAll(pages,pageSize);
        return ResponseEntity.ok(users);
    }

    // Endpoint to delete any user by id
    @DeleteMapping(path = "users/{id}")
    public ResponseEntity<?> deleteUserFound(@PathVariable("id") Long id) {
        try {
            UserResponseDto deletedUser = adminService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Failed to delete item by id : "+id);
        }
    }

    // Endpoint to delete any item by id
    @DeleteMapping(path = "items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id){
        try{
            ItemDeleteResponseDto deletedItem = adminService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedItem);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Failed to delete item by id : "+id);
        }
    }
}
