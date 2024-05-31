package dev.findrecipesbyingredient.service.recipe;

import dev.findrecipesbyingredient.model.Recipe;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class RecipeService {

    private final UserRepository userRepository;

    public RecipeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String  addFavoriteRecipe(String userId,  Recipe recipe){

        // Convert the userId from String to ObjectId
        ObjectId objectId = new ObjectId(userId);

        // Retrieve the user by their ObjectId
        Optional<User> optionalUser = userRepository.findById(objectId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user.getFavoriteRecipes());
            user.getFavoriteRecipes().add(recipe);
            userRepository.save(user);
            return "Recipe added to favorites successfully.";
        } else {
            throw new RuntimeException("User not found");
        }

    }
}
