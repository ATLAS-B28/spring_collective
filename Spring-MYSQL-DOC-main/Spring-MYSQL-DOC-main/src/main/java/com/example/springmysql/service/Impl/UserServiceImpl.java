package com.example.springmysql.service.Impl;

import com.example.springmysql.dto.UserDTO;
import com.example.springmysql.entity.User;
import com.example.springmysql.exception.EmailAlreadyExistsException;
import com.example.springmysql.exception.ErrorDetails;
import com.example.springmysql.exception.ResourceNotFoundException;
import com.example.springmysql.mapper.UserMapperStructAuto;
import com.example.springmysql.respository.UserRepository;
import com.example.springmysql.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//previously we use modelMapper now we will
//be doing modelStruct

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    //private ModelMapper modelMapper;
    //<- we are using constructor dependency injection
    @Override
    public UserDTO createUser(UserDTO userDto){
       // User user = UserMapper.mapToUser(userDto);
       // User user = modelMapper.map(userDto,User.class);//Object source and object destination

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = UserMapperStructAuto.MAPPER.mapToUser(userDto);//we use mapstruct
        User savedUser = userRepository.save(user);
        //first call the save function and save it as user in the database through repository
        //convert to dto
        //UserDTO finalForm = UserMapper.mapToUserDTO(savedUser);
        //UserDTO finalForm = modelMapper.map(savedUser,UserDTO.class);
        UserDTO finalForm = UserMapperStructAuto.MAPPER.maptoUserDTO(savedUser);
        return finalForm;
    }

    @Override
    public UserDTO getUserById(Long userId){

        User user =  userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User","id",userId)
                );
        //older implementation
        //Optional<User> optionalUser = userRepository.findById(userId);
        //returns the optional type of user
        //User user = optionalUser.get();//get the user body
        //return UserMapper.mapToUserDTO(user);
        //return modelMapper.map(user,UserDTO.class);
        return UserMapperStructAuto.MAPPER.maptoUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        //we have a list which is a collection of objects
        //to convert aka operate on them
        //to do that we have streams that have map function
        //and apply it on the list of objects
        //and then convert to list again
        //previous implementations
        /*(user/*jpa entity)->UserMapperStructAuto.MAPPER.mapToUserDTO(user)  modelMapper.map(user,UserDTO.class)*/
              //  .map(UserMapper::mapToUserDTO)
              //  .collect(Collectors.toList());
        return users.stream()
                    .map(UserMapperStructAuto.MAPPER::maptoUserDTO)
                    .collect(Collectors.toList());
    }
    @Override
    public UserDTO updateUser(UserDTO user) { //user here is dto object
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User","id", user.getId())
                );//.get() - older implementation
        //user repository to find by id and get the object using get() method
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        //we get the name form another user object aka
        //the incoming one and then set to the existing
        //user
        User updatedUser = userRepository.save(existingUser);
        //saving and taking the return value
        //and converting to dto for presentation
        UserDTO userDto = UserMapperStructAuto.MAPPER.maptoUserDTO(updatedUser);
        //UserDTO userDto = modelMapper.map(updatedUser,UserDTO.class);
        //UserMapper.mapToUserDTO(updatedUser);
        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("User","id",userId)
                        );
        userRepository.deleteById(userId);
    }


}
