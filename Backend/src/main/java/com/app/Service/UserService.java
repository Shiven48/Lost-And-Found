package com.app.Service;

import com.app.Models.DTO.Item.ItemRequestDto;
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

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            ItemMapper itemMapper,
            ItemRepository itemRepository,
            ItemService itemService,
            CredentialsRepository credentialsRepository,
            AdminRepository adminRepository,
            AdminMapper adminMapper
    ) {
        this.itemMapper = itemMapper;
        this.userMapper = userMapper;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
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

    private User fetchUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + userId));
    }

    private Credentials fetchCredentialsById(Long id){
        return credentialsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user with Id"+ id));
    }

    private boolean validateUser(UserDto user) {
        return user != null;
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
        if(!(validateId(id) && validateUser(requestedUser))){
            throw new IllegalArgumentException("The parameters are not legal");
        }
        if(!checkIfIdPresent(id)){
            throw new NoSuchElementException("There is no element with the ID :"+id);
        }
        User oldUser = fetchUserByUserId(id);
        System.out.println(oldUser);
        User updatedUser = userRepository.save(userMapper.UserDtoToUser(oldUser,requestedUser));
        System.out.println(updatedUser);
        return userMapper.userToUserResponseDto(updatedUser,updatedUser.getCredentials());
    }

    public UserResponseDto updateCredentials(){
        return null;
    }

    public UserResponseDto updateLostFoundItems(){
        return null;
    }




//    ----------    X ------------------- X ----------------------- X ------------------- X ----------------------- X -------------------

    // Endpoint to fetch a list of all users.
    public List<UserResponseDto> userAll() {
//        try {
//            List<User> users = userRepository.findAll();
//            return users.stream()
//                    .map(userMapper::userToUserResponseDto)
//                    .toList();
//        } catch (Exception e) {
//            throw new RuntimeException("Cant fetch all users", e);
//        }
        return List.of(new UserResponseDto(100L,null,true));
    }

    // Fetch Lost items by a specific user
    public <T> List<T> getLostItemsForAUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        try {
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("User not found");
            }
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            List<Item> LostItems = user.getItemsLost();
            return LostItems.stream()
                    .map(lost -> {
                        return (T) itemService.validate(lost);
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get lost items");
        }
    }

    // Fetch Found items by a specific user
    public <T> List<T> getFoundItemsForAUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        try {
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("User not found");
            }
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            List<Item> FoundItems = user.getItemsFound();
            return FoundItems.stream()
                    .map(lost -> {
                        return (T) itemService.validate(lost);
                    })
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get lost items");
        }
    }

    // Endpoint to delete a user by their ID.
    @Transactional
    public UserResponseDto deleteUser(Long id) {
//        if (id == null) {
//            throw new IllegalArgumentException("Id cannot be null");
//        }
//        try {
//            UserResponseDto response_user = null;
//            User user = userRepository.findById(id)
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
//            if (user != null) {
//                response_user = userMapper.userToUserResponseDto(user);
//                userRepository.delete(user);
//            }
//            return response_user;
//        } catch (Exception e) {
//            throw new RuntimeException("Cannot delete the user");
//        }
        return new UserResponseDto(100L,null,true);
    }

    // For Adding Lost Item for a specific user
    @Transactional
    public UserLostItemsDto addLostItem(Long id, ItemRequestDto requestItem) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (requestItem == null) {
            throw new IllegalArgumentException("requestItem is null");
        }
        try {
            User user = helperForFetchUser(id);
            Item item = new Item();
            itemMapper.ItemFromRequestDto(item, requestItem);
            Item resp_item = itemRepository.save(item);
            user.addLostItem(resp_item);
            return userMapper.toUserLostItemsDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Cannot add lost item");
        }
    }

    private User helperForFetchUser(Long id){
        return new User();
    }

    // For Adding Found Item for a specific user
    @Transactional
    public UserFoundItemsDto addFoundItem(Long id, ItemRequestDto requestItem) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (requestItem == null) {
            throw new IllegalArgumentException("requestItem is null");
        }
        try {
            User user = helperForFetchUser(id);
            Item item = new Item();
            itemMapper.ItemFromRequestDto(item, requestItem);
            Item resp_item = itemRepository.save(item);
            user.addFoundItem(resp_item);
            return userMapper.toUserFoundItemsDto(user);
        } catch (Exception e) {
            throw new RuntimeException("Cannot add lost item");
        }
    }

}
