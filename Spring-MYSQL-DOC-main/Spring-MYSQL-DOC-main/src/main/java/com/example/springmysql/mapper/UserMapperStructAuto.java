package com.example.springmysql.mapper;

import com.example.springmysql.dto.UserDTO;
import com.example.springmysql.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapperStructAuto {
    //we need to use them
    //we need to have object initialized
    //we use the utility class
    UserMapperStructAuto MAPPER = Mappers
                                  .getMapper(
                                           UserMapperStructAuto.class
                                  );
    //getMapper is a factory class that will
    //create an implementation
    //of this interface at compilation time

    //conversion between each other
    UserDTO maptoUserDTO(User user);
    User mapToUser(UserDTO userDTO);
}//mapstruct will create an implementation at
//compilation time
//field names need to be same
//if different then we need
//to use @Mapping(source="email",target="emailAddress")
