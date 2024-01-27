package com.example.BookMyShowProject.DTOs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AddShowRequest {

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate showDate;

    private String movieName;

    private int screenId;

    private int theatreId;

    private int normalSeatPrice;

    private int premiumSeatPrice;

}
