package com.app.Mapper;

import com.app.DTO.User.UserDto;
import com.app.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getCredentials().getEmail()
        );
    }
}
