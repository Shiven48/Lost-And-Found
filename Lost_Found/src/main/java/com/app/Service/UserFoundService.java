package com.app.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.Entity.UserFound;
import com.app.Repository.UserFoundRepository;

@Service
public class UserFoundService {

	private UserFoundRepository userFoundRepository;
	
	UserFoundService(UserFoundRepository userFoundRepository) {
		this.userFoundRepository = userFoundRepository;
	}
	
	public UserFound getById(Long id) {
		try {
			return userFoundRepository
									.findById(id)
									.orElse(new UserFound());
		} catch(Exception e) {
			 throw new RuntimeException("Failed to retrieve user with id: "+id, e);
		}
	}
	
	public UserFound postUserFound(UserFound user_Found) {
		try {
			UserFound user = new UserFound();
			
			userFoundRepository.save(user_Found);
			// Check tags
			// Add user
			return null;
		} catch(Exception e) {
			throw new RuntimeException("Failed to post user: ", e);
		}
	}

	public List<UserFound> getuserFoundAll() {
		try {
			List<UserFound> AllUserFound = userFoundRepository.findAll();
			return AllUserFound.isEmpty() ? Collections.emptyList() : AllUserFound;	
		} catch(Exception e) {
            throw new RuntimeException("Failed to retrieve users", e);
		}
	}

}
