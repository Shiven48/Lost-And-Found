package com.app.Entity.Mapper;

import com.app.Entity.DTO.User.*;
import com.app.Entity.Models.Credentials;
import com.app.Entity.Models.User;
import com.app.Repository.CredentialsRepository;
import com.app.Service.CredentialService;
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

    public User userRequestDtoToUser(User user, UserRequestDto userRequestDto, Credentials credential) {
//        user.setName(userRequestDto.name());
//        user.setLoggedIn(userRequestDto.isLoggedIn());
//
//
//
//        user.setCredentials(credential);
//        credential.setUser(user);

        return new User();
    }

    public User userRequestDtoToUser(User user, UserRequestDto userRequestDto) {
        user.setName(userRequestDto.name());
        user.setLoggedIn(userRequestDto.isLoggedIn());

        return user;
    }

    public UserRequestDto UserToUserRequestDto(User user) {
        return new UserRequestDto(
                user.getName(),
                user.getLoggedIn(),
//                user.getCredentials().getId()
                12083L
        );
    }

    public UserDto toUserDto(User user){
        return new UserDto(
//                user.getUserId(),
                user.getName(),
//                user.getCredentials().getEmail()
                "some@gmail.com"
        );
    }

    public UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(
//                user.getUserId(),
                user.getName(),
                user.getLoggedIn(),
//                user.getRegistrationDate(),
//                user.getLastModified(),
//                CredentialMapper.ToCredentialResponseDto(user.getCredentials())
                CredentialMapper.ToCredentialResponseDto(new User())
        );
    }

    public UserLostItemsDto toUserLostItemsDto(User user){
        return new UserLostItemsDto(
//                user.getUserId(),
                user.getName(),
                user.getLoggedIn(),
                user.getItemsLost()
        );
    }

    public UserFoundItemsDto toUserFoundItemsDto(User user) {
        return new UserFoundItemsDto(
//                user.getUserId(),
                user.getName(),
                user.getLoggedIn(),
                user.getItemsFound()
        );
    }
}
