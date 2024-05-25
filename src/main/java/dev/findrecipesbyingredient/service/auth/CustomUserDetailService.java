package dev.findrecipesbyingredient.service.auth;

import dev.findrecipesbyingredient.model.auth.CustomUserDetail;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        User user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username)
        );

        return new CustomUserDetail(user);
    }
}
