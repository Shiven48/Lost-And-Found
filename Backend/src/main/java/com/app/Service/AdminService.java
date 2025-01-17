package com.app.Service;

import com.app.DTO.User.UserResponseDto;
import com.app.Entity.User;
import com.app.Interface.AdminRepository;
import com.app.Mapper.UserMapper;
import com.app.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AdminService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<UserResponseDto> fetchAll() {
        try{
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(userMapper::userToUserResponseDto)
                    .toList();
        } catch(Exception e){
            throw new RuntimeException("Cant fetch all users",e);
        }
    }

    @Transactional
    public UserResponseDto deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        try {
            UserResponseDto response_user = null;
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            if (user != null) {
                response_user = userMapper.userToUserResponseDto(user);
                userRepository.delete(user);
            }
            return response_user;
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete the user");
        }
    }

}
