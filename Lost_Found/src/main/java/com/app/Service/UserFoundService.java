package com.app.Service;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Entity.UserFound;
import com.app.Repository.UserFoundRepository;

@SuppressWarnings("All")
@Service
public class UserFoundService {

	@Autowired
	private UserFoundRepository userFoundRepository;
	@Autowired
	private CredentialService credentialService;


	UserFoundService(UserFoundRepository userFoundRepository) {
		this.userFoundRepository = userFoundRepository;
	}

	// Get found user by id
	public UserFound getById(Long id) {
		try {
			return userFoundRepository
					.findById(id)
					.orElse(new UserFound());
		} catch(Exception e) {
			throw new RuntimeException("Failed to retrieve user with id: "+id, e);
		}
	}

	// post User Found
	public UserFound postUserFound(UserFound user_Found, HttpSession session) {
		try {
			Credentials temp_creds = (Credentials) session.getAttribute("created_credentials");
			System.out.println(temp_creds);

			Credentials credentials = new Credentials();
			credentials.setCredentialsId(temp_creds.getCredentialsId());
			credentials.setEmail(temp_creds.getEmail());
			credentials.setPassword(temp_creds.getPassword());

			user_Found.setCredentials(credentials);
			return userFoundRepository.save(user_Found);
		} catch(Exception e) {
			throw new RuntimeException("Failed to post user found", e);
		}
	}

	// Get all found users
	public List<UserFound> getuserFoundAll() {
		try {
			List<UserFound> AllUserFound = userFoundRepository.findAll();
			return AllUserFound.isEmpty() ? Collections.emptyList() : AllUserFound;
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve users", e);
		}
	}

}




