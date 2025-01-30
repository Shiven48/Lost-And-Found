package com.app.Models.DTO.User;

import com.app.Models.Enums.Lost_Found;
import jakarta.validation.constraints.NotBlank;

public record UserDto(

        @NotBlank(message = "Email is required")
        Boolean isLoggedIn,

        @NotBlank(message = "User type required(LOST/FOUND)")
        Lost_Found lost_found

) {
}
