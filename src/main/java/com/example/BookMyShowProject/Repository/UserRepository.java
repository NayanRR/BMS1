package com.example.BookMyShowProject.Repository;

import com.example.BookMyShowProject.Models.TheatreUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<TheatreUser, Integer> {
    TheatreUser findUserByUserName(String username);
    TheatreUser findUserByEmailId(String emailId);

}
