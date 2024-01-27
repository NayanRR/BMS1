package com.example.BookMyShowProject.Models;

import com.example.BookMyShowProject.Enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @Column(unique = true)
    private String movieName;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    private LocalDate releaseDate;

    private String directorName;


    //One movie can have many shows
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Shows> showsList=new ArrayList<>();




}
