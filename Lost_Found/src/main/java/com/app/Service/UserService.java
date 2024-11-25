package com.app.Service;

import com.app.Entity.Credentials;
import com.app.Entity.User;
import com.app.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CredentialService credentialService;

    public UserService(UserRepository userRepository, CredentialService credentialService) {
        this.userRepository = userRepository;
        this.credentialService = credentialService;
    }

    // Endpoint to create a new user (either `UserLost` or `UserFound`).
    public User postUser(User user) {
        if(user == null){
            throw new IllegalArgumentException("user is null");
        }
        try{
            User resp_user = new User();

            resp_user.setName(user.getName());
            resp_user.setLoggedIn(user.getLoggedIn());

            Long cred_id = user.getCredentials().getId();
            Credentials credential = credentialService.getCredentialsById(cred_id);

            if(credential != null){
                resp_user.setCredentials(credential);
                credential.setUser(resp_user);
            }

           return userRepository.save(resp_user);
        } catch(Exception e){
            throw new RuntimeException("User cannot be created",e);
        }
    }

    // Endpoint to fetch a list of all users.
    public List<User> userFoundAll() {
        try{
            return userRepository.findAll();
        } catch(Exception e){
            throw new RuntimeException("Cant fetch all users",e);
        }
    }

    // Endpoint to fetch a user by their ID.
    public User userById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try{
            return userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        } catch(Exception e){
            throw new RuntimeException("Cant fetch user by id : "+id,e);
        }
    }

    // Endpoint to update a user by his id
    public User updateUsers(Long id, User user) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if(user == null){
            throw new IllegalArgumentException("user cannot be null");
        }
        try{
            User resp_user = userById(id);

            if(user.getName() != null){
                resp_user.setName(user.getName());
            }
            if(user.getLoggedIn() != null){
                resp_user.setLoggedIn(user.getLoggedIn());
            }

            // Retrieving the credential by its id
            Credentials credential = credentialService.getCredentialsById(user.getCredentials().getId());

            if(credential != null){
                if(credential.getEmail() != null){
                    credential.setEmail(credential.getEmail());
                }
                if(credential.getPassword() != null){
                    credential.setPassword(credential.getPassword());
                }
                resp_user.setCredentials(credential);
                credential.setUser(resp_user);
            }
            return userRepository.save(resp_user);
        } catch(Exception e){
            throw new RuntimeException("Cannot update the user");
        }
    }

    // Endpoint to delete a user by their ID.
    public User deleteUser(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try{
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            if(user != null){
                userRepository.delete(user);
            }
            return user;
        } catch(Exception e){
            throw new RuntimeException("Cannot delete the user");
        }
    }

}
