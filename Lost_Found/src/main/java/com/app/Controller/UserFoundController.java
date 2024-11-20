package com.app.Controller;

import com.app.Entity.Credentials;
import com.app.Entity.User;
import com.app.Entity.UserFound;
import com.app.Service.UserFoundService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RequestMapping(value = "api/")
@RestController
public class UserFoundController {

	private UserFoundService userService;

	public UserFoundController(UserFoundService userService) {
		this.userService = userService;
	}

	// Endpoint to get all users
	@GetMapping(path = "users/userfound",name = "foundUserAll")
	public ResponseEntity<List<UserFound>> fetchAll() {
		List<UserFound> user = userService.getuserFoundAll();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
	}

	// Endpoint to get all users by their id
	@GetMapping(path = "users/userfound/{id}",name = "fetchById")
	public ResponseEntity<User> fetchById(@PathVariable("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
	}

	// Endpoint to post a user
	@PostMapping(consumes = {"application/json","application/xml"},path = "/users/userfound")
	public ResponseEntity<User> postUserFound(@RequestBody UserFound user_Found,HttpSession session) {
		UserFound userFound= userService.postUserFound(user_Found,session);
		return ResponseEntity.status(HttpStatus.CREATED).body(userFound);
	}

	//  Endpoint to update user details.
	@PutMapping(name="updateUserFound",path = "users/userfound/{id}")
	public ResponseEntity<User> updateUserFound(@RequestBody UserFound userFound,@PathVariable("id") Long id) {
		UserFound result_userFound = userService.updateUserFound(userFound,id);
		return ResponseEntity.status(HttpStatus.OK).body(result_userFound);
	}

	// Endpoint to delete a user by their ID.
	@DeleteMapping(name="deleteuserFound",path = "/users/userfound/{id}")
	public ResponseEntity<User> deleteUserFound(@PathVariable("id") Long id) {
		try {
			if (id == null) {
				throw new IllegalArgumentException("User id cannot be null");
			}
			UserFound resp_userFound = userService.deleteUserById(id);
			if (resp_userFound == null) {
				throw new IllegalArgumentException("Invalid user id:" + id);
			}
			return ResponseEntity.status(HttpStatus.OK).body(resp_userFound);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete user found", e);
		}
	}
}


