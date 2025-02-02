package com.app.Controller;

import com.app.Models.DTO.Item.AddedResponseDto;
import com.app.Models.DTO.User.*;
import com.app.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RequestMapping(value = "/api/users")
@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Endpoint to get user by ID
	@GetMapping(path="/{id}",name = "userById")
	public ResponseEntity<UserResponseDto> fetchById(@PathVariable("id") Long userId) {
		UserResponseDto user = userService.userById(userId);
		return ResponseEntity.ok(user);
	}

	// Endpoint to create a new user
	@PostMapping(consumes = {"application/json","application/xml"},path = "")
	public ResponseEntity<UserResponseDto> postUserFound(@RequestBody UserRequestDto user) {
		UserResponseDto resp_user= userService.postUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp_user);
	}

	// Endpoint to update user details.
	@PutMapping(name="updateUser",path = "/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto requestedUser){
		UserResponseDto resp_user = userService.updateUsers(id,requestedUser);
		return ResponseEntity.ok(resp_user);
	}

	// =============== X =============== X =============== X =============== X =============== X =============== X ===============

	// Endpoint to add found Item for the given user
	@PutMapping(path = "/{userId}/founditem/{itemId}")
	public ResponseEntity<AddedResponseDto> foundItem(@PathVariable("userId") Long userId,
													  @PathVariable("itemId") Long itemId) {
		try{
			return ResponseEntity.ok(userService.addFoundItem(userId,itemId));
		} catch(Exception e){
			throw new RuntimeException("Failed to add lost item for user with Id : "+userId, e);
		}
	}

	// Endpoint to add lost Item for the given user.
	@PutMapping(path = "/{userId}/lostitem/{itemId}")
	public ResponseEntity<AddedResponseDto> lostItem(@PathVariable("userId") Long userId,
													 @PathVariable("itemId") Long itemId) {
		try{
			return ResponseEntity.ok(userService.addLostItem(userId,itemId));
		} catch(Exception e){
			throw new RuntimeException("Failed to add lost item for user with Id : "+userId, e);
		}
	}

	// Endpoint to get all Lost items by a single user
	@GetMapping(path = "/{id}/lostitem")
	public <T> ResponseEntity<List<T>> getLostItems(@PathVariable("id") Long id){
		try{
			return ResponseEntity.ok(userService.getLostItemsForAUser(id));
		}catch(Exception e){
			throw new RuntimeException("Failed to get founded items by user with Id : "+id, e);
		}
	}

	// Endpoint to get all found items by a single user
	@GetMapping(path = "/{id}/founditem")
	public <T> ResponseEntity<List<T>> getFoundItems(@PathVariable("id") Long id){
		try{
			return ResponseEntity.ok(userService.getFoundItemsForAUser(id));
		}catch(Exception e){
			throw new RuntimeException("Failed to get founded items by user with Id : "+id, e);
		}
	}

}


