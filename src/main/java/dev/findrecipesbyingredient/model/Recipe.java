package dev.findrecipesbyingredient.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
public class Recipe {

    @Id
    private String Id;
    private String name;


    public Recipe(String name, String Id) {
        this.name = name;
        this.Id=Id;

    }

    public String getId() {
        return Id;
    }
    public void setId(String Id){
        this.Id=Id;
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



    public void setName(String name) {
        this.name = name;
    }
}
