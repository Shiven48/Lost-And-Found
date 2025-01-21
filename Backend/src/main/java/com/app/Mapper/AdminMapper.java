package com.app.Mapper;

import com.app.DTO.Admin.AdminResponseDto;
import com.app.Entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminResponseDto toAdminResponseDto(Admin admin) {
        return new AdminResponseDto(
                admin.getAdminId(),
                admin.getEmail()
        );
    }
}
