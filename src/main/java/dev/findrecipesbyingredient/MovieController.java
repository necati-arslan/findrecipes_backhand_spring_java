package dev.findrecipesbyingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;


@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){ //ResponseEntity sınıfı, HTTP yanıtının yanıt kodunu, başlıklarını ve gövdesini içeren bir sarmalayıcıdır. Bu

        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);

    }

    //@GetMapping("/{id}")
    //public ResponseEntity<Optional<Movie>> getSingelMovie(@PathVariable ObjectId id){
     //   return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(id), HttpStatus.OK);

    //}

    @GetMapping("/{year}")
    public ResponseEntity<Optional<List<Movie>>> getByYearMovie(@PathVariable Integer year){
        return new ResponseEntity<Optional<List<Movie>>>(movieService.serviceFindByYear(year), HttpStatus.OK);

    }
}
