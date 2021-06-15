package com.epam.tr.controller;

import com.epam.tr.dao.entities.AppUser;
import com.epam.tr.exceptions.WrongUserCredentials;
import com.epam.tr.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/newUser")
    public void createNewUser(@RequestBody AppUser appUser) throws WrongUserCredentials {
        AppUser newAppUser = new AppUser(appUser.getLogin(),encoder.encode(appUser.getPassword()));
        service.create(newAppUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllUsers() throws JsonProcessingException {
        return ResponseEntity.status(OK).body(new ObjectMapper().writeValueAsString(service.getAllUsers()));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getUserById(@PathVariable int id) throws JsonProcessingException {
        return  ResponseEntity.status(OK).body(new ObjectMapper().writeValueAsString(service.getUserById(id)));
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable int userId, @RequestBody AppUser newAppUser) throws WrongUserCredentials {
        AppUser oldAppUser = service.getUserById(userId);
        service.update(oldAppUser, newAppUser);
        return new ResponseEntity(CREATED);
    }

    @PatchMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) throws WrongUserCredentials {
        AppUser appUser = service.getUserById(userId);
        service.delete(appUser);
        return new ResponseEntity(NO_CONTENT);
    }
}
