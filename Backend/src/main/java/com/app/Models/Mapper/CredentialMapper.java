package com.app.Models.Mapper;

import com.app.Models.DTO.Credentials.CredentialsRequestDto;
import com.app.Models.DTO.Credentials.CredentialsResponseDto;
import com.app.Models.Entities.Credentials;
import org.springframework.stereotype.Component;

@Component
public class CredentialMapper {

    public static CredentialsResponseDto ToCredentialResponseDto(Credentials credential) {
        return new CredentialsResponseDto(
                credential.getId(),
                credential.getName(),
                credential.getEmail(),
                credential.getRoles()
        );
    }

    public Credentials CredReqToCredentials(Credentials credentials,CredentialsRequestDto dto) {
        credentials.setPassword(dto.password());
        credentials.setRoles(dto.roles());
        credentials.setName(dto.name());
        return credentials;
    }
}
