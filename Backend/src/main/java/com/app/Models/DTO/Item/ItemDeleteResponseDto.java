package com.app.Entity.DTO.Item;

public record ItemDeleteResponseDto(Long id,
                                    String name) {
    public ItemDeleteResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
