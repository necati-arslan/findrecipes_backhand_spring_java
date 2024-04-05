package dev.findrecipesbyingredient;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies")
@Data//get and set method, hashcode,tostring()...
@AllArgsConstructor //Bu özellik, sınıfınıza tüm alanları içeren bir constructor ekler. Bu, her alanı kullanarak bir nesne oluşturmak için kullanışlıdır. Örneğin:
@NoArgsConstructor //@NoArgsConstructor: Bu özellik, sınıfınıza parametresiz bir constructor ekler. Bu, sınıfınızın bir nesnesini parametre vermeden de oluşturabilmenizi sağlar. Örneğin:


public class Movie {
    @Id
    private ObjectId id;
    private String plot;

    private Integer year;
    private List<Review> reviews;





}
