package com.app.Models.Mapper;

import com.app.Models.DTO.Admin.AdminRequestDto;
import com.app.Models.DTO.Admin.AdminResponseDto;
import com.app.Models.DTO.User.UserRequestDto;
import com.app.Models.Entities.Admin;
import com.app.Models.Entities.Credentials;
import com.app.Models.Entities.User;
import com.app.Models.Enums.Lost_Found;
import com.app.Models.Interface.UserType;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminResponseDto toAdminResponseDto(Admin admin) {
        return new AdminResponseDto(
                admin.getId(),
                admin.getCredentials().getName(),
                admin.getLoggedIn()
        );
    }

    public AdminRequestDto toAdminRequestDto(UserRequestDto userRequestDto) {
        return new AdminRequestDto(
                userRequestDto.email(),
                userRequestDto.password(),
                userRequestDto.roles(),
                userRequestDto.name(),
                userRequestDto.isLoggedIn()
        );
    }

    public Admin adminRequestDtoToUser(Admin admin, Credentials credential, AdminRequestDto adminRequestDto) {
        admin.setLoggedIn(adminRequestDto.isLoggedIn());
        admin.setCredentials(credential);
        return admin;
    }
}
