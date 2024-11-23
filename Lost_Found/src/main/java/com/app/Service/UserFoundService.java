package com.app.Service;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Entity.Credentials;
import com.app.Entity.UserFound;
import com.app.Repository.UserFoundRepository;

@SuppressWarnings("All")
@Service
public class UserFoundService {

	private UserFoundRepository userFoundRepository;
	private CredentialService credentialService;
	private EntityManager entityManager;

	@Autowired
	UserFoundService(UserFoundRepository userFoundRepository, CredentialService credentialService, EntityManager entityManager) {
		this.userFoundRepository = userFoundRepository;
		this.credentialService = credentialService;
		this.entityManager = entityManager;
	}

	// Get all found users service
	public List<UserFound> getuserFoundAll() {
		try {
			List<UserFound> AllUserFound = userFoundRepository.findAll();
			return AllUserFound.isEmpty() ? Collections.emptyList() : AllUserFound;
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve users", e);
		}
	}

	// Get found user by id service
	public UserFound getById(Long id) {
		try {
			System.out.println("Here : "+id);
			return userFoundRepository
					.findById(id)
					.orElse(new UserFound());
		} catch(Exception e) {
			throw new RuntimeException("Failed to retrieve user with id: "+id, e);
		}
	}

	// post User Found service
	public UserFound postUserFound(UserFound user_Found, HttpSession session) {
		try {
			Credentials temp_creds = (Credentials) session.getAttribute("credential");

			Credentials credentials = entityManager.find(Credentials.class, temp_creds.getCredentialsId());
			if(credentials == null){
				credentials = entityManager.merge(temp_creds);
			}

			user_Found.setCredentials(credentials);
			credentials.setUser(user_Found);

			if (user_Found.getName() == null || user_Found.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("Name cannot be blank");
			}

			return userFoundRepository.save(user_Found);
		} catch(Exception e) {
			throw new RuntimeException("Failed to post user found", e);
		}
	}

	// Update User Found service
	@Transactional
	public UserFound updateUserFound(UserFound userFound, Long id) {
		try {
			UserFound resp_userFound = getById(id);

			if (userFound.getPlace() != null) {
				resp_userFound.setPlace(userFound.getPlace());
			}
			if (userFound.getTime() != null) {
				resp_userFound.setTime(userFound.getTime());
			}
			if (userFound.getTags() != null) {
				resp_userFound.setTags(userFound.getTags());
			}
			if (userFound.getName() != null) {
				resp_userFound.setName(userFound.getName());
			}
			if (userFound.getRegistration_Date() != null) {
				resp_userFound.setRegistration_Date(userFound.getRegistration_Date());
			}
			if (userFound.getLastModified() != null) {
				resp_userFound.setLastModified(userFound.getLastModified());
			}
			resp_userFound.setLoggedIn(userFound.isLoggedIn());

			if (userFound.getCredentials() != null) {
				Credentials newCredentials = userFound.getCredentials();
				Credentials existingCredentials = resp_userFound.getCredentials();

				if (newCredentials.getEmail() != null) {
					existingCredentials.setEmail(newCredentials.getEmail());
				}
				if (newCredentials.getPassword() != null) {
					existingCredentials.setPassword(newCredentials.getPassword());
				}
			}
			return userFoundRepository.save(resp_userFound);
		} catch (Exception e) {
			throw new RuntimeException("Failed to update user found", e);
		}
	}

	@Transactional
	public UserFound deleteUserById(Long id) {
		try {
			if(id == null) {
				throw new IllegalArgumentException("User id cannot be null");
			}
			UserFound resp_userFound= userFoundRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
			// Explicitly loading the tags to avoid LazyInitializationException
			resp_userFound.getTags().size();
			userFoundRepository.delete(resp_userFound);
			return resp_userFound;
		} catch(Exception e) {
			throw new RuntimeException("Failed to delete user found", e);
		}
	}
}

