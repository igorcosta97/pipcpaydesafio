package com.picpay.picpacy.controllers;

import com.picpay.picpacy.domain.user.User;
import com.picpay.picpacy.dtos.UserDTO;
import com.picpay.picpacy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        var newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var users = this.userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}
