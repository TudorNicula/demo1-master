package com.example.demo.controller;

import com.example.demo.entity.DTO.UserDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    UserRepository userRepository;
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        logger.info("Retrieved all users");
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        logger.info("Retrieve users with id {} ", id);
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody UserDTO updateUser, @PathVariable Long id) {
        logger.info("Update user with id {} ", id);
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> {
            u.setUsername(updateUser.getUsername());
            u.setEmail(updateUser.getEmail());
            u.setPassword(updateUser.getPassword());
            return userRepository.save(u);
        }).orElseGet(() -> {
            updateUser.setId(id);
            return userRepository.save(updateUser);
        });
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) {
        logger.info("Added user with id {} ", newUser.getId());
        return userRepository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        logger.info("Deleted user with id {} ", id);
        userRepository.deleteById(id);
    }
}
