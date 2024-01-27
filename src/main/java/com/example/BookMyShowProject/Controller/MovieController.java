package com.example.BookMyShowProject.Controller;

import com.example.BookMyShowProject.DTOs.AddMovieRequest;
import com.example.BookMyShowProject.Service.MovieService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping("/AllLatestMovie")
    public List<String> ShowAllMovie(){
       return  movieService.ShowAllMovie();
    }
    @PostMapping("/addMovie")
    public ResponseEntity<String> AddMovie(@RequestBody AddMovieRequest addMovieRequest) {
        try{
            String res=movieService.addMovie(addMovieRequest);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
