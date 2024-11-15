package com.app.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Entity.User;
import com.app.Entity.UserFound;
//import com.app.Entity.UserLost;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import com.app.Repository.UserRepository;

//(48,"Shiven","Shiven@gmail.com","Shiven@123",LocalDateTime.now(),
//		LocalDateTime.now(),"Virar", LocalTime.MIDNIGHT,tags)

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	UserService() {
		List<String> tags = new ArrayList<String>();
		tags.add("Shiven");
		User[] arr1 = {new UserFound("Virar", LocalTime.MIDNIGHT,tags)};
	}
	
	public User getById(Long id) {
		return userRepository.findById(id).orElse(new UserFound());
	}

}
