package com.app.Controller;

import com.app.DTO.User.UserDto;
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

	// Endpoint to create a new user (either `UserLost` or `UserFound`).
	@PostMapping(consumes = {"application/json","application/xml"},path = "/users")
	public ResponseEntity<User> postUserFound(@RequestBody User user) {
		User resp_user= userService.postUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp_user);
	}

	// Endpoint to get all users
	@GetMapping(path = "users",name = "foundUserAll")
	public ResponseEntity<List<User>> fetchAll() {
		List<User> user = userService.userFoundAll();
		return ResponseEntity.ok(user);
	}

	// Endpoint to get user by ID
	@GetMapping(path="users/{id}",name = "userById")
	public ResponseEntity<User> fetchById(@PathVariable("id") Long id) {
		User user = userService.userById(id);
		return ResponseEntity.ok(user);
	}

	// Endpoint to update user details.
	@PutMapping(name="updateUser",path = "users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		User resp_user = userService.updateUsers(id,user);
		return ResponseEntity.ok(resp_user);
	}

	// Endpoint to delete a user by their ID.
	@DeleteMapping(name="deleteuserFound",path = "/users/{id}")
	public ResponseEntity<User> deleteUserFound(@PathVariable("id") Long id) {
		try {
			User user = userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete user found", e);
		}
	}


	// Endpoint to add lost Item for the given user.
	// Endpoint to add found Item for the given user.
	// Endpoint to fetch all lost items for the given user.
	// Endpoint to fet all founded items for the given user.


}


