package dev.findrecipesbyingredient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recipe {

    @Id
    private String Id;
    private String name;

    public Recipe(String id, String name) {
        Id = id;
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
