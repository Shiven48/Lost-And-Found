package com.app.Controller;

import com.app.DTO.User.UserResponseDto;
import com.app.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // Endpoint to fetch all Users by Admin
    @GetMapping(path="admin/users")
    public ResponseEntity<List<UserResponseDto>> fetchAll() {
        List<UserResponseDto> users = adminService.fetchAll();
        return ResponseEntity.ok(users);
    }

    // Endpoint to delete any user by id
    @DeleteMapping(path = "admin/users/{id}")
    public ResponseEntity<UserResponseDto> deleteUserFound(@PathVariable("id") Long id) {
        try {
            UserResponseDto user = adminService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user found", e);
        }
    }

    // Endpoint to delete any item by id
    

}
