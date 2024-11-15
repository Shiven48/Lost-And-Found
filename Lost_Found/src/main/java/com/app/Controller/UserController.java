package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.Entity.User;
import com.app.Service.UserService;

//1. **Create User**
//- `POST /users`
//- Endpoint to create a new user (either `UserLost` or `UserFound`).

@RestController
public class UserController {

	private UserService userService;

	@GetMapping(path = "users/{id}",name = "fetchById",consumes = {"application/json","application/xml"})
	public ResponseEntity<User> fetchById(@PathVariable ("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
	}
	
//2. **Get User by ID**
//   - `GET /users/{id}`
//   - Endpoint to fetch a user by their ID.
//
//3. **Update User**
//   - `PUT /users/{id}`
//   - Endpoint to update user details.
//
//4. **Delete User**
//   - `DELETE /users/{id}`
//   - Endpoint to delete a user by their ID.
//
//5. **Get All Users**
//   - `GET /users`
//   - Endpoint to fetch a list of all users.


	
}
