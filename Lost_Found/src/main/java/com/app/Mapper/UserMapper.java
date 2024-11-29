package com.app.Mapper;

import com.app.DTO.Credentials.CredentialsResponseDto;
import com.app.DTO.User.*;
import com.app.Entity.Credentials;
import com.app.Entity.Item;
import com.app.Entity.User;
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

    public User userRequestDtoToUser(User user, UserRequestDto userRequestDto) {

        user.setName(userRequestDto.name());
        user.setLoggedIn(userRequestDto.isLoggedIn());

        Long cred_id = userRequestDto.credentialId();

        Credentials credential = credentialsRepository.findById(cred_id)
                .orElseThrow(() -> new RuntimeException("Credential not found"));

        user.setCredentials(credential);
        credential.setUser(user);

        return user;
    }

    public UserDto toUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getCredentials().getEmail()
        );
    }

    public UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getName(),
                user.getLoggedIn(),
                user.getRegistrationDate(),
                user.getLastModified(),
                CredentialMapper.ToCredentialResponseDto(user.getCredentials())
        );
    }

    public UserRequestDto UserToUserRequestDto(User user) {
        return new UserRequestDto(
          user.getName(),
          user.getLoggedIn(),
          user.getCredentials().getId()
        );
    }

    public UserLostItemsDto toUserLostItemsDto(User user){
        return new UserLostItemsDto(
                user.getUserId(),
                user.getName(),
                user.getLoggedIn(),
                user.getItemsLost()
        );
    }

    public UserFoundItemsDto toUserFoundItemsDto(User user) {
        return new UserFoundItemsDto(
                user.getUserId(),
                user.getName(),
                user.getLoggedIn(),
                user.getItemsFound()
        );
    }
}
