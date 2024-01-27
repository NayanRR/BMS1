package com.example.BookMyShowProject.Service;

import com.example.BookMyShowProject.DTOs.AddMovieRequest;
import com.example.BookMyShowProject.Models.Movie;
import com.example.BookMyShowProject.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(AddMovieRequest addMovieRequest) {

        Movie movie=new Movie().builder()
                .movieName(addMovieRequest.getMovieName())
                .directorName(addMovieRequest.getDirectorName())
                .genre(addMovieRequest.getGenre())
                .releaseDate(addMovieRequest.getReleaseDate())
                .build();
        movieRepository.save(movie);
        return "The movie is Successfully added in DB";

    }

    public List<String> ShowAllMovie() {
        List<String> movieName=new ArrayList<>();
        List<Movie> movieList=movieRepository.findAll();
        for(Movie movie:movieList){
            String name=movie.getMovieName();
            movieName.add(name);
        }
        return movieName;
    }
}
