package dev.findrecipesbyingredient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @GetMapping
    public ResponseEntity<String> allMovies(){ //ResponseEntity sınıfı, HTTP yanıtının yanıt kodunu, başlıklarını ve gövdesini içeren bir sarmalayıcıdır. Bu
        return new ResponseEntity<String>("all movies",HttpStatus.OK);
    }
}
