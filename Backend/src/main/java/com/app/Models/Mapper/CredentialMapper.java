package com.app.Models.Mapper;

import com.app.Models.DTO.Credentials.CredentialsResponseDto;
import com.app.Models.Entities.Credentials;
import com.app.Models.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class CredentialMapper {

    public static CredentialsResponseDto ToCredentialResponseDto(Credentials credential) {
        return new CredentialsResponseDto(
//                credential.getId(),
                credential.getEmail()
        );
    }

//    public Credentials toCredential(CredentialsRequestDto dto, String role){
//        return new User();
//    }
}
