package dev.findrecipesbyingredient.service.recipe;

import dev.findrecipesbyingredient.model.Recipe;
import dev.findrecipesbyingredient.model.auth.User;
import dev.findrecipesbyingredient.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final UserRepository userRepository;

    public RecipeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String addFavoriteRecipe(String userId, String recipeId) {
        // Convert the userId from String to ObjectId
        ObjectId objectId = new ObjectId(userId);

        // Retrieve the user by their ObjectId
        Optional<User> optionalUser = userRepository.findById(objectId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getFavoriteRecipesIds().contains(recipeId)) {
                user.getFavoriteRecipesIds().add(recipeId);
                userRepository.save(user);
            } else {
                return "Recipe is already in favorites.";
            }
            return "Recipe added to favorites successfully.";
        } else {
            throw new RuntimeException("User not found");
        }
    }


    public List<String> getFavoriteRecipeIds(String userId) {
        // Convert the userId from String to ObjectId
        ObjectId objectId = new ObjectId(userId);

        // Retrieve the user by their ObjectId
        Optional<User> optionalUser = userRepository.findById(objectId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getFavoriteRecipesIds();
        } else {
            throw new RuntimeException("User not found");
        }
    }



}
