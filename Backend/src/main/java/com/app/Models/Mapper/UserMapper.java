package com.app.Models.Mapper;

import com.app.Models.DTO.User.*;
import com.app.Models.Entities.Credentials;
import com.app.Models.Entities.User;
import com.app.Models.Enums.Lost_Found;
import com.app.Models.Interface.UserType;
import com.app.Repository.CredentialsRepository;
import com.app.Service.CredentialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final CredentialService credentialService;
    private final CredentialsRepository credentialsRepository;

    @Autowired
    public UserMapper(CredentialService credentialService, CredentialsRepository credentialsRepository) {
        this.credentialService = credentialService;
        this.credentialsRepository = credentialsRepository;
    }

    public UserResponseDto userToUserResponseDto(UserType user, Credentials credential) {
        return new UserResponseDto(
                user.getId(),
                credential.getName(),
                user.getLoggedIn()
        );
    }

    public User userRequestDtoToUser(User user, Credentials credential, UserRequestDto userRequestDto) {
        user.setLoggedIn(userRequestDto.isLoggedIn());
        user.setLost_found(Lost_Found.LOST);
        user.setCredentials(credential);
        return user;
    }

    public Credentials toUserResponseAdmin(@Valid UserRequestDto userRequestDto, String role) {
        return new Credentials(
                userRequestDto.email(),
                userRequestDto.password(),
                userRequestDto.name(),
                role
        );
    }

    public Credentials toUserResponseUser(@Valid UserRequestDto userRequestDto, String role) {
        return new Credentials(
                userRequestDto.email(),
                userRequestDto.password(),
                userRequestDto.name(),
                role
        );
    }

    public UserRequestDto UserToUserRequestDto(User user) {
       return null;
    }

    public UserDto toUserDto(User user){
        return null;
    }

    public UserLostItemsDto toUserLostItemsDto(User user){
        return null;
    }

    public UserFoundItemsDto toUserFoundItemsDto(User user) {
        return null;
    }

    public User UserDtoToUser(User oldUser, UserDto dto) {
        oldUser.setLost_found(dto.lost_found());
        oldUser.setLoggedIn(dto.isLoggedIn());
        return oldUser;
    }
}
