package com.app.Models.DTO.Item;

public record ItemDeleteResponseDto(Long id, String name) {
    public ItemDeleteResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
