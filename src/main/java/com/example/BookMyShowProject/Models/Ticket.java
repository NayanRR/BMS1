package com.example.BookMyShowProject.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    private String movieName;

    private String theatreName;

    private String theatreAdd;

    private String screenNo;

    private LocalDate movieDate;

    private LocalTime showTime;

    private int ticketCost;

    //show seats;

    //Many tickets can be own by one user
    @ManyToOne
    @JoinColumn
    private TheatreUser theatreUser;

    //One ticket can own multiple show seats
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<ShowSeats> showSeatsList=new ArrayList<>();


}
