package com.app.Entity.Mapper;

import com.app.Entity.DTO.Credentials.CredentialsResponseDto;
import com.app.Entity.Models.Credentials;
import org.springframework.stereotype.Component;

@Component
public class CredentialMapper {

    public static CredentialsResponseDto ToCredentialResponseDto(Credentials credential) {
        return new CredentialsResponseDto(
//                credential.getId(),
                credential.getEmail()
        );
    }




}
