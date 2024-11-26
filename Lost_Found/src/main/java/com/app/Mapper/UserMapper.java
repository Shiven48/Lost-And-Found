package com.app.Mapper;

import com.app.DTO.User.UserDto;
import com.app.Entity.User;

public class UserMapper {

    public static UserDto toUserDto(User user){
        return new UserDto(
          user.getUserId(),
                user.getName(),
                user.getCredentials().getEmail()
        );
    }
}
