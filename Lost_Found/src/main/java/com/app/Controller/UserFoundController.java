package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.User;
import com.app.Entity.UserFound;
import com.app.Service.UserFoundService;


@RequestMapping(value = "api/")
@RestController
public class UserFoundController {

	private UserFoundService userService;
	
	public UserFoundController(UserFoundService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "users/userfound",name = "foundUserAll")
	public ResponseEntity<List<UserFound>> fetchAll() {
		List<UserFound> user = userService.getuserFoundAll();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
	}

	@GetMapping(path = "users/userfound/{id}",name = "fetchById")
	public ResponseEntity<User> fetchById(@PathVariable ("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
	}
	
	@PostMapping(consumes = {"application/json","application/xml"},path = "/users/userfound")
	public ResponseEntity<User> postUserFound(@RequestBody UserFound user_Found) {
		UserFound userFound= userService.postUserFound(user_Found);
		return ResponseEntity.status(HttpStatus.CREATED).body(userFound);
	}
	
}









//3. **Update User**
// - `PUT /users/{id}`
// - Endpoint to update user details.
//
//4. **Delete User**
// - `DELETE /users/{id}`
// - Endpoint to delete a user by their ID.
//
//5. **Get All Users**
// - `GET /users`
// - Endpoint to fetch a list of all users.
