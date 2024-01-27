package com.example.BookMyShowProject.DTOs;

import com.example.BookMyShowProject.Enums.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class AddMovieRequest {

    private String movieName;

    @Enumerated(value= EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    private String directorName;
}
