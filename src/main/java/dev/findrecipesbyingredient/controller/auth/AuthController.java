package dev.findrecipesbyingredient.controller.auth;


import dev.findrecipesbyingredient.dto.auth.CreateUserRequest;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import dev.findrecipesbyingredient.service.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private  final UserRepository userRepository;
    private  final UserService userService;

    public AuthController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        List<User> user= userRepository.findAll();
        return user;

    }

    @PostMapping("/addNewUser")
    public User addUser(@RequestBody CreateUserRequest request){

        System.out.println("----------------" +request.username());

        return userService.createUser(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequest request) {
        try {
            User newUser = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully: " + newUser.getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
