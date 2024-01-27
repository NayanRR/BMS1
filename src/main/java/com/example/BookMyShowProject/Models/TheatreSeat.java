package com.example.BookMyShowProject.Models;

import com.example.BookMyShowProject.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TheatreSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    private String seatNo;

    private SeatType seatType;

    @ManyToOne
    @JoinColumn
    private TheatreScreen theatreScreen;


}
