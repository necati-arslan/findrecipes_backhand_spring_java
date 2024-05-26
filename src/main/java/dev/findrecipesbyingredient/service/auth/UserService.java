package dev.findrecipesbyingredient.service.auth;

import dev.findrecipesbyingredient.dto.auth.CreateUserRequest;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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




}
