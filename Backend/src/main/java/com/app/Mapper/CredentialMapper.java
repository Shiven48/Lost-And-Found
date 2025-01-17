package com.app.Mapper;

import com.app.DTO.Credentials.CredentialsResponseDto;
import com.app.Entity.Credentials;
import org.springframework.stereotype.Component;

@Component
public class CredentialMapper {

    public static CredentialsResponseDto ToCredentialResponseDto(Credentials credential) {
        return new CredentialsResponseDto(
                credential.getId(),
                credential.getEmail()
        );
    }




}
