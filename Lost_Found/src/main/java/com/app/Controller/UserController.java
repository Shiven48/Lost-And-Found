package com.app.Controller;

import com.app.DTO.Item.ItemRequestDto;
import com.app.DTO.Item.ItemResponseDto;
import com.app.DTO.User.*;
import com.app.Entity.User;
import com.app.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RequestMapping(value = "api/")
@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Endpoint to get all users
	@GetMapping(path = "users",name = "foundUserAll")
	public ResponseEntity<List<UserResponseDto>> fetchAll() {
		List<UserResponseDto> user = userService.userFoundAll();
		return ResponseEntity.ok(user);
	}

	// Endpoint to get user by ID
	@GetMapping(path="users/{id}",name = "userById")
	public ResponseEntity<UserResponseDto> fetchById(@PathVariable("id") Long id) {
		UserResponseDto user = userService.userById(id);
		return ResponseEntity.ok(user);
	}

	// Endpoint to create a new user (either `UserLost` or `UserFound`).
	@PostMapping(consumes = {"application/json","application/xml"},path = "/users")
	public ResponseEntity<UserResponseDto> postUserFound(@RequestBody UserRequestDto user) {
		UserResponseDto resp_user= userService.postUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp_user);
	}

	// Endpoint to update user details.
	@PutMapping(name="updateUser",path = "users/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		UserResponseDto resp_user = userService.updateUsers(id,user);
		return ResponseEntity.ok(resp_user);
	}

	// Endpoint to delete a user by their ID.
	@DeleteMapping(name="deleteuserFound",path = "/users/{id}")
	public ResponseEntity<UserResponseDto> deleteUserFound(@PathVariable("id") Long id) {
		try {
			UserResponseDto user = userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete user found", e);
		}
	}

	// Endpoint to add lost Item for the given user.
	@PutMapping(path = "users/{id}/lostitem")
	public ResponseEntity<UserLostItemsDto> lostItem(@PathVariable("id") Long id,
													 @RequestBody ItemRequestDto requestItem
	) {
		try{
			return ResponseEntity.ok(userService.addLostItem(id,requestItem));
		} catch(Exception e){
			throw new RuntimeException("Failed to add lost item for user with Id : "+id, e);
		}
	}

	// Endpoint to add found Item for the given user
	@PutMapping(path = "users/{id}/founditem")
	public ResponseEntity<UserFoundItemsDto> foundItem(@PathVariable("id") Long id,
													   @RequestBody ItemRequestDto requestItem
	) {
		try{
			return ResponseEntity.ok(userService.addFoundItem(id,requestItem));
		} catch(Exception e){
			throw new RuntimeException("Failed to add lost item for user with Id : "+id, e);
		}
	}

	@GetMapping(path = "users/{id}/lostitem")
	public <T> ResponseEntity<List<T>> getLostItems(@PathVariable("id") Long id){
		try{
			return ResponseEntity.ok(userService.getLostItems(id));
		}catch(Exception e){
			throw new RuntimeException("Failed to get founded items by user with Id : "+id, e);
		}
	}


	@GetMapping(path = "users/{id}/founditem")
	public <T> ResponseEntity<List<T>> getFoundItems(@PathVariable("id") Long id){
		try{
			return ResponseEntity.ok(userService.getFoundItems(id));
		}catch(Exception e){
			throw new RuntimeException("Failed to get founded items by user with Id : "+id, e);
		}
	}

}


