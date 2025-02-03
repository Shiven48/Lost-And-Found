package com.app.Service;

import com.app.Models.DTO.Item.AddedResponseDto;
import com.app.Models.DTO.User.*;
import com.app.Models.Entities.Admin;
import com.app.Models.Entities.Credentials;
import com.app.Models.Entities.Item;
import com.app.Models.Entities.User;
import com.app.Models.Interface.UserType;
import com.app.Models.Mapper.AdminMapper;
import com.app.Models.Mapper.ItemMapper;
import com.app.Models.Mapper.UserMapper;
import com.app.Repository.AdminRepository;
import com.app.Repository.CredentialsRepository;
import com.app.Repository.ItemRepository;
import com.app.Repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CredentialsRepository credentialsRepository;
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PaginationAndSorting paginationAndSorting;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            ItemMapper itemMapper,
            ItemRepository itemRepository,
            ItemService itemService,
            CredentialsRepository credentialsRepository,
            AdminRepository adminRepository,
            AdminMapper adminMapper,
            PaginationAndSorting paginationAndSorting
    ) {
        this.itemMapper = itemMapper;
        this.userMapper = userMapper;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.paginationAndSorting = paginationAndSorting;
    }

    // Endpoint to fetch a user by their ID.
    public UserResponseDto userById(Long userId) {
        checkIfIdNull(userId);
        User user = fetchUserByUserId(userId);
        return userMapper.userToUserResponseDto(user, user.getCredentials());
    }

    // Endpoint to create a new user (`UserLost` is default).
    @Transactional
    public UserResponseDto postUser(@Valid UserRequestDto userRequestDto) {
        Credentials credential = checkCredentials(userRequestDto);
        credential = credentialsRepository.save(credential);
        UserType user = PersistUser(credential,userRequestDto);
        return userMapper.userToUserResponseDto(user,credential);
    }

    private Credentials checkCredentials(UserRequestDto userRequestDto){
        return userRequestDto.roles().equals("ROLE_ADMIN") ?
                userMapper.toUserResponseAdmin(userRequestDto, userRequestDto.roles()) :
                userMapper.toUserResponseUser(userRequestDto, userRequestDto.roles());
    }

    private UserType PersistUser (Credentials credential, UserRequestDto userRequestDto){
        return credential.getRoles().equals("ROLE_ADMIN") ?
                adminRepository.save(adminMapper.adminRequestDtoToUser(new Admin(), credential, adminMapper.toAdminRequestDto(userRequestDto))) :
                userRepository.save(userMapper.userRequestDtoToUser(new User(), credential, userRequestDto));
    }

    private void checkIfIdNull(Long userId) {
        if (userId != null) {
            return;
        }
        throw new IllegalArgumentException("User id should not be null");
    }

    public User fetchUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + userId));
    }

    private boolean checkUserDto(UserDto userDto) {
        return userDto != null;
    }

    private boolean checkUser(Object user){
        return user!=null;
    }

    private boolean validateId(Long id) {
        return id != null;
    }

    private boolean checkIfIdPresent(@Valid Long id) {
        return userRepository.existsById(id);
    }

    // Endpoint to update a user by his id
    @Transactional
    public UserResponseDto updateUsers(@Valid Long id,@Valid UserDto requestedUser) {
        if(!(validateId(id) && checkUserDto(requestedUser))){
            throw new IllegalArgumentException("The parameters are not legal");
        }
        if(!checkIfIdPresent(id)){
            throw new NoSuchElementException("There is no element with the ID :"+id);
        }
        User oldUser = fetchUserByUserId(id);
        User updatedUser = userRepository.save(userMapper.UserDtoToUser(oldUser,requestedUser));
        return userMapper.userToUserResponseDto(updatedUser,updatedUser.getCredentials());
    }

    // Endpoint to delete a user by their ID.
    @Transactional
    public UserResponseDto deleteUser(Long id) {
        if(!validateId(id)){
            throw new IllegalArgumentException("User id is null");
        }
        User user = fetchUserByUserId(id);
        if(checkUser(user)) {
            userRepository.delete(user);
        }
        return userMapper.userToUserResponseDto(user,user.getCredentials());
    }

    // For Adding Found Item for a specific user
    @Transactional
    public AddedResponseDto addFoundItem(Long userId, Long itemId) {
        try {
            checkIfIdNull(userId);
            checkIfIdNull(itemId);
            User user = fetchUserByUserId(userId);
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("The item id cannot be null"));
            item.setFinder(user);
            user.addFoundItem(item);
            return itemMapper.toAddedResponseDto(itemId,item);
        } catch (Exception e) {
            throw new RuntimeException("Cannot add lost item");
        }
    }

    // For Adding Lost Item for a specific user
    @Transactional
    public AddedResponseDto addLostItem(Long userId, Long itemId) {
        try {
            checkIfIdNull(userId);
            checkIfIdNull(itemId);
            User user = fetchUserByUserId(userId);
            Item item = itemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("The item id cannot be null"));
            item.setOwner(user);
            user.addLostItem(item);
            return itemMapper.toAddedResponseDto(itemId,item);
        } catch (Exception e) {
            throw new RuntimeException("Cannot add lost item");
        }
    }

    public List<User> userAll() {
        return userRepository.findAll().stream().toList();
    }

    // Endpoint to fetch a list of all users.
    public List<UserResponseDto> userAll(int pages, int pageSize) {
        paginationAndSorting.validatePaginate(pages, pageSize);
        return userRepository.findAll(PageRequest.of(pages, pageSize))
                .stream()
                .map(user -> {
                    return userMapper.userToUserResponseDto(user,user.getCredentials());
                })
                .toList();
    }

    // Fetch Lost items by a specific user
    public <T> List<T> getLostItemsForAUser(Long id) {
        checkIfIdNull(id);
        try {
            if (!checkIfIdPresent(id)) {
                throw new IllegalArgumentException("User not found");
            }
            return fetchUserByUserId(id)
                    .getItemsLost()
                    .stream()
                    .map(lost -> {
                        return (T)itemService.validate(lost);
                    }).toList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get lost items");
        }
    }

    // Fetch Found items by a specific user
    public <T> List<T> getFoundItemsForAUser(Long id) {
        checkIfIdNull(id);
        try {
            if (!checkIfIdPresent(id)) {
                throw new IllegalArgumentException("User not found");
            }
            return fetchUserByUserId(id)
                    .getItemsFound()
                    .stream()
                    .map(lost -> {
                        return (T) itemService.validate(lost);
                    }).toList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get lost items");
        }
    }
}
