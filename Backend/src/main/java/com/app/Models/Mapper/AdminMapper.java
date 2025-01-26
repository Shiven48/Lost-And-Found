package com.app.Entity.Mapper;

import com.app.Entity.DTO.Admin.AdminResponseDto;
import com.app.Entity.Models.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminResponseDto toAdminResponseDto(Admin admin) {
        return new AdminResponseDto(
//                admin.getAdminId(),
                admin.getEmail(),
                "ROLE_ADMIN"
        );
    }
}
