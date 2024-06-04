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

    @PostMapping("/{userId}/add-favorite-recipes")
    public ResponseEntity<String> addFavoriteRecipe(@PathVariable String userId, @RequestBody Recipe recipe) {
        try {
            System.out.println("user---"+userId);
            String result = recipeService.addFavoriteRecipe(userId, recipe);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{userId}/favorite-recipes")
    public ResponseEntity<List<Recipe>> getFavoriteRecipes(@PathVariable String userId) {
        try {
            System.out.println("user---"+userId);
            List<Recipe> favoriteRecipes = recipeService.getFavoriteRecipes(userId);
            return new ResponseEntity<>(favoriteRecipes, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
