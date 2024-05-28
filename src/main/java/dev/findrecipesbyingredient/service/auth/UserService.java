package dev.findrecipesbyingredient.service.auth;

import dev.findrecipesbyingredient.dto.auth.AuthResponse;
import dev.findrecipesbyingredient.dto.auth.CreateUserRequest;
import dev.findrecipesbyingredient.dto.auth.LoginRequest;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public Optional<User>  checkUserByUsername(String username){
       return userRepository.findByUsername(username);

    }




    public User createUser(CreateUserRequest request){

        Optional<User> existingUser = checkUserByUsername(request.username());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + request.username());

        }
        User newUser = new User(request.username(),passwordEncoder.encode(request.password()),request.roles());
        return userRepository.save(newUser);

    }

    public AuthResponse loginUser (LoginRequest loginRequest){

        try {
            // Creating UsernamePasswordAuthenticationToken object
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(token);

            // If authentication is successful, generate a JWT token
            if (authentication.isAuthenticated()) {
                String jwt= jwtService.generateToken(loginRequest.username());
                return new AuthResponse(jwt,loginRequest.username());
            } else {
                throw new UsernameNotFoundException("Authentication failed for user: " + loginRequest.username());
            }
        } catch (AuthenticationException e) {
            // Handle authentication exceptions appropriately
            throw new UsernameNotFoundException("Authentication failed for user: " + loginRequest.username(), e);
        }
    }


    }






