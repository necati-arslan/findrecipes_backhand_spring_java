package dev.findrecipesbyingredient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
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

    @Override
    public String toString() {
        return "Recipe{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                '}';
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
