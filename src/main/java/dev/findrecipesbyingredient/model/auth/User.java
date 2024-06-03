package dev.findrecipesbyingredient.model.auth;

import dev.findrecipesbyingredient.model.Recipe;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String roles;
    @DBRef
    private List<Recipe> favoriteRecipes=new ArrayList<>();

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(List<Recipe> recipes) {
        this.favoriteRecipes = favoriteRecipes;
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
