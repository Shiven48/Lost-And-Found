package com.app.Mapper;

import com.app.DTO.Credentials.CredentialsResponseDto;
import com.app.DTO.User.UserDto;
import com.app.DTO.User.UserRequestDto;
import com.app.DTO.User.UserResponseDto;
import com.app.Entity.Credentials;
import com.app.Entity.User;
import com.app.Service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final CredentialService credentialService;

    @Autowired
    public UserMapper(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    public User userRequestDtoToUser(User user, UserRequestDto userRequestDto) {

        user.setName(user.getName());
        user.setLoggedIn(user.getLoggedIn());

        Long cred_id = user.getCredentials().getId();
        CredentialsResponseDto credentialsResponseDto = credentialService.getCredentialsById(cred_id);

        if(credentialsResponseDto != null){
            Credentials credential = new Credentials();
            credential.setId(cred_id);
            if(credentialsResponseDto.email() != null)
            {
                credential.setEmail(credentialsResponseDto.email());
            }
            if(credentialsResponseDto.password() != null)
            {
                credential.setPassword(credentialsResponseDto.password());
            }

            user.setCredentials(credential);
            credential.setUser(user);
        }
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
}
