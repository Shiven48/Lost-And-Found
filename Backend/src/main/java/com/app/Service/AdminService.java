package com.app.Service;

import com.app.Models.DTO.Admin.AdminResponseDto;
import com.app.Models.DTO.Item.ItemDeleteResponseDto;
import com.app.Models.DTO.User.UserResponseDto;
import com.app.Models.Mapper.AdminMapper;
import com.app.Repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserService userService;
    private final AdminMapper adminMapper;
    private final ItemService itemService;

    public AdminService(
                            AdminRepository adminRepository,
                            UserService userService,
                            AdminMapper adminMapper,
                            ItemService itemService
    ){
        this.adminRepository = adminRepository;
        this.userService = userService;
        this.adminMapper = adminMapper;
        this.itemService = itemService;
    }

    @Transactional
    public UserResponseDto deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    public List<UserResponseDto> userFoundAll(){
        return userService.userAll();
    }

    public List<AdminResponseDto> fetchAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toAdminResponseDto)
                .toList();
    }

    public ItemDeleteResponseDto deleteItem(Long id) {
        return itemService.deleteItem(id);
    }
}
