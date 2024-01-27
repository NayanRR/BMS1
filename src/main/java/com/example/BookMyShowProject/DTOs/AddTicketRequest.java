package com.example.BookMyShowProject.DTOs;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class AddTicketRequest {
    private Integer userId;
    @Column(nullable = false)
    private String movieName;
    private Integer theatreId;
    private String city;
    private LocalDate showDate;
    private LocalTime showTime;
    private List<String> requestedSeatNos;

}
