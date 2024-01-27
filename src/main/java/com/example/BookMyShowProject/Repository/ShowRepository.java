package com.example.BookMyShowProject.Repository;

import com.example.BookMyShowProject.Models.Movie;
import com.example.BookMyShowProject.Models.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Shows,Integer> {

    List<Shows> findShowsByMovieAndStartTimeAndShowDate(Movie movie, LocalTime showTime, LocalDate showDate);
}
