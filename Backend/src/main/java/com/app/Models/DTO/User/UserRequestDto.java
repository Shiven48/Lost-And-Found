package com.app.Entity.DTO.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @NotBlank(message = "Name is required")
        @Size(min=2, max = 25, message = "Name must be less than 25 characters")
        String name,

        @NotNull(message = "isLoggedIn is required")
        Boolean isLoggedIn,

        @NotNull(message = "Credentials ID is required")
        Long credentialId
) { }


