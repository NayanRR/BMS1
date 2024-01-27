package com.example.BookMyShowProject.Repository;

import com.example.BookMyShowProject.Models.ShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeats,Integer> {
}
