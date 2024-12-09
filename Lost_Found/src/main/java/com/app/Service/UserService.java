package com.app.Service;

import com.app.DTO.Credentials.CredentialsResponseDto;
import com.app.DTO.Item.ItemRequestDto;
import com.app.DTO.Item.ItemResponseDto;
import com.app.DTO.User.UserFoundItemsDto;
import com.app.DTO.User.UserLostItemsDto;
import com.app.DTO.User.UserRequestDto;
import com.app.DTO.User.UserResponseDto;
import com.app.Entity.Credentials;
import com.app.Entity.Item;
import com.app.Entity.User;
import com.app.Mapper.ItemMapper;
import com.app.Mapper.UserMapper;
import com.app.Repository.CredentialsRepository;
import com.app.Repository.ItemRepository;
import com.app.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    public UserService(UserRepository userRepository, UserMapper userMapper, ItemMapper itemMapper, ItemRepository itemRepository, ItemService itemService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    // Endpoint to fetch a list of all users.
    public List<UserResponseDto> userFoundAll() {
        try{
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(userMapper::userToUserResponseDto)
                    .toList();
        } catch(Exception e){
            throw new RuntimeException("Cant fetch all users",e);
        }
    }

    // Endpoint to fetch a user by their ID.
    public UserResponseDto userById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try{
            User user =  userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            return userMapper.userToUserResponseDto(user);
        } catch(Exception e){
            throw new RuntimeException("Cant fetch user by id : "+id,e);
        }
    }

    // Endpoint to create a new user (either `UserLost` or `UserFound`).
    @Transactional
    public UserResponseDto postUser(UserRequestDto userRequestDto) {
        if(userRequestDto == null){
            throw new IllegalArgumentException("user is null");
        }
        try{
            User user = new User();
            userMapper.userRequestDtoToUser(user,userRequestDto);
            User response_user = userRepository.save(user);
            return userMapper.userToUserResponseDto(response_user);
        } catch(Exception e){
            throw new RuntimeException("User cannot be created",e);
        }
    }

    @Transactional
    // Endpoint to update a user by his id
    public UserResponseDto updateUsers(Long id, User user) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if(user == null){
            throw new IllegalArgumentException("user cannot be null");
        }
        try{
            if(!userRepository.existsById(id)){
                throw new IllegalArgumentException("User not found");
            }
            User resp_user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));

            UserRequestDto userRequestDto = userMapper.UserToUserRequestDto(user);

            User final_user =  userRepository.save(userMapper.userRequestDtoToUser(resp_user, userRequestDto));

            return userMapper.userToUserResponseDto(final_user);
        } catch(Exception e){
            throw new RuntimeException("Cannot update the user");
        }
    }

    // Endpoint to delete a user by their ID.
    @Transactional
    public UserResponseDto deleteUser(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try{
            UserResponseDto response_user = null;
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            if(user != null){
               response_user = userMapper.userToUserResponseDto(user);
                userRepository.delete(user);
            }
            return response_user;
        } catch(Exception e){
            throw new RuntimeException("Cannot delete the user");
        }
    }

    @Transactional
    public UserLostItemsDto addLostItem(Long id, ItemRequestDto requestItem) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if(requestItem == null){
            throw new IllegalArgumentException("requestItem is null");
        }
        try{
            if(!userRepository.existsById(id)){
                throw new IllegalArgumentException("User not found");
            }
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            Item item = new Item();
            itemMapper.ItemFromRequestDto(item,requestItem);
            Item resp_item = itemRepository.save(item);
            user.addLostItem(resp_item);
            return userMapper.toUserLostItemsDto(user);
        } catch(Exception e){
            throw new RuntimeException("Cannot add lost item");
        }
    }

    public UserFoundItemsDto addFoundItem(Long id, ItemRequestDto requestItem) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        if(requestItem == null){
            throw new IllegalArgumentException("requestItem is null");
        }
        try{
            if(!userRepository.existsById(id)){
                throw new IllegalArgumentException("User not found");
            }
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
            Item item = new Item();
            itemMapper.ItemFromRequestDto(item,requestItem);
            Item resp_item = itemRepository.save(item);
            user.addFoundItem(resp_item);
            return userMapper.toUserFoundItemsDto(user);
        } catch(Exception e){
            throw new RuntimeException("Cannot add lost item");
        }
    }

    public <T> List<T> getLostItems(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try{
            if(!userRepository.existsById(id)){
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
        } catch(Exception e){
            throw new RuntimeException("Cannot get lost items");
        }
    }

    public <T> List<T> getFoundItems(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        try{
            if(!userRepository.existsById(id)){
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
        } catch(Exception e){
            throw new RuntimeException("Cannot get lost items");
        }
    }
}
