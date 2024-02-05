package com.example.springmysql.controller;

import com.example.springmysql.dto.UserDTO;
import com.example.springmysql.entity.User;
import com.example.springmysql.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "User Controller V2"
)
@RestController//<- makes it a rest controller method
@AllArgsConstructor//<- only one parameterized constructor on this bean for the spring to see
@RequestMapping("/api/v2/users")
public class UserController {
    //inject user service dependency
    private UserService userService;

    @Operation(
            summary = "Create a new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "User created successfully"
    )
    //create user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user){
       UserDTO savedUser = userService.createUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get a user by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User found"
    )
    @GetMapping("/{userId}")//<-this is part of uri template
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId){
        UserDTO user = userService.getUserById(userId);//returns an object
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Users found"
    )
    @GetMapping("/all")//<-this is part of uri template
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @Operation(
            summary = "Update a user by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User updated successfully"
    )
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId,
                                           @RequestBody @Valid UserDTO user){
        user.setId(userId);//set the user id as we are using path variable
        UserDTO updatedUser = userService.updateUser(user);//as user will be passed in
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a user by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User deleted successfully"
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }
}
