package com.app.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.Entity.User;
import com.app.Entity.UserFound;
//import com.app.Entity.UserLost;
//
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import com.app.Repository.UserFoundRepository;

//(48,"Shiven","Shiven@gmail.com","Shiven@123",LocalDateTime.now(),
//		LocalDateTime.now(),"Virar", LocalTime.MIDNIGHT,tags)

@Service
public class UserFoundService {

	private UserFoundRepository userFoundRepository;
	
	UserFoundService(UserFoundRepository userFoundRepository) {
		this.userFoundRepository = userFoundRepository;
	}
	
	public UserFound getById(Long id) {
		return userFoundRepository.findById(id).orElse(new UserFound());
	}
	
	public UserFound postUserFound(UserFound user_Found) {
		return null;
	}

	public List<UserFound> getuserFoundAll() {
		List<UserFound> AllUserFound = userFoundRepository.findAll();
		if(AllUserFound != null) {
			return AllUserFound;
		}
		return new ArrayList<UserFound>();
	}

}
