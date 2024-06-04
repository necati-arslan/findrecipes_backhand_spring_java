package dev.findrecipesbyingredient.controller;

import dev.findrecipesbyingredient.model.Recipe;
import dev.findrecipesbyingredient.service.auth.UserService;
import dev.findrecipesbyingredient.service.recipe.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipeController {

    private  final UserService userService;
    private final RecipeService recipeService;

    public RecipeController(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }


    @PostMapping("/{userId}/favorite-recipes/{recipeId}")
    public ResponseEntity<String> addFavoriteRecipe(@PathVariable String userId, @PathVariable String recipeId) {
        try {
            String result = recipeService.addFavoriteRecipe(userId, recipeId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @GetMapping("/{userId}/favorite-recipes")
    public ResponseEntity<List<String>> getFavoriteRecipeIds(@PathVariable String userId) {
        try {
            List<String> favoriteRecipeIds = recipeService.getFavoriteRecipeIds(userId);
            return ResponseEntity.ok(favoriteRecipeIds);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }




}
