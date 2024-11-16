package com.app.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Entity.UserFound;
import com.app.Repository.UserFoundRepository;

@Service
public class UserFoundService {

	@Autowired
	private UserFoundRepository userFoundRepository;
	
	@Autowired
	private CredentialService credentialService;

	
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
		System.out.println(user_Found);
		try {
			// Would be changed if added Login Functionality
			if(!user_Found.isLoggedIn()) {
				// Here Change to return login page
				throw new RuntimeException("User not Logged In");
			}

			if(user_Found.getTags().isEmpty()) {
				user_Found.setTags(new ArrayList<String>());
			}
			
			Long id = user_Found.getUser_Id();
			System.out.println(id);
			Credentials credential = credentialService.getCredentialsById(id);
			user_Found.setCredentials(credential);
			System.out.println("User Found After Adding Credentials : "+user_Found);
			// Add user
			return userFoundRepository.save(user_Found);
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
