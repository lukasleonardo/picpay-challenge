package com.teste.picpay.controllers;

import com.teste.picpay.domain.user.User;
import com.teste.picpay.dtos.UserDTO;
import com.teste.picpay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = this.userService.createUser(userDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){

        List<User> users = this.userService.findAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
