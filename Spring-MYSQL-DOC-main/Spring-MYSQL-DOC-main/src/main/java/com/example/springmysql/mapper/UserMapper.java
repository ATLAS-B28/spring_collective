package com.example.springmysql.mapper;

import com.example.springmysql.dto.UserDTO;
import com.example.springmysql.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user){
        //convert user to dto
        UserDTO userDto = new UserDTO(
                user.getId()
                ,user.getFirstName()
                ,user.getLastName()
                ,user.getEmail()
        );
        return userDto;
        //maps the entity to dto
    }

    //do the reverse of the above function
    public static User mapToUser(UserDTO userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
