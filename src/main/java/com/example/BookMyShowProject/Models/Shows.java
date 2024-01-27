package com.example.BookMyShowProject.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showId;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate showDate;

    @ManyToOne
    @JoinColumn
    private TheatreScreen theatreScreen;


    //Many movie can have one shows
    @ManyToOne
    @JoinColumn
    private Movie movie;

    //one show can have many show seats
    @OneToMany(mappedBy = "shows",cascade = CascadeType.ALL)
    private List<ShowSeats> showSeatsList=new ArrayList<>();

}
