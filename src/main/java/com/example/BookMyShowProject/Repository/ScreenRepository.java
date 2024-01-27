package com.example.BookMyShowProject.Repository;

import com.example.BookMyShowProject.Models.TheatreScreen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<TheatreScreen,Integer> {
}
