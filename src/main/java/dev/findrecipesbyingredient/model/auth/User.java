package dev.findrecipesbyingredient.model.auth;

import dev.findrecipesbyingredient.model.Recipe;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")


public class User {
    private ObjectId id;
    private String username;
    private String password;
    private String roles;
    @DBRef(lazy = true)
    private List<Recipe> recipes=new ArrayList<>();

    public User(String username, String password, String roles,List<Recipe> recipes) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.recipes= recipes != null ? recipes : new ArrayList<>();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ObjectId getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


}
