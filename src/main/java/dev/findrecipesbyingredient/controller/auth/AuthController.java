package dev.findrecipesbyingredient.controller.auth;

import dev.findrecipesbyingredient.dto.auth.AuthResponse;
import dev.findrecipesbyingredient.service.auth.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import dev.findrecipesbyingredient.dto.auth.CreateUserRequest;
import dev.findrecipesbyingredient.dto.auth.LoginRequest;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import dev.findrecipesbyingredient.service.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private  final UserRepository userRepository;
    private  final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;



    public AuthController(UserRepository userRepository, UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse authResponse = userService.loginUser(request);
            return ResponseEntity.ok(authResponse);
        } catch (UsernameNotFoundException e) {
            // Catch any authentication exception and handle it appropriately
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid username or password",request.username()));
        }
    }

    @PostMapping("/findUserById")
    public ResponseEntity<User> findByUserId(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        Optional<User> user = userService.findByUserId(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
