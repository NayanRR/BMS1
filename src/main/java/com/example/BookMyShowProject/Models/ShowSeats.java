package com.example.BookMyShowProject.Models;

import com.example.BookMyShowProject.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ShowSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    private String seatNo;

    private boolean isAvailable;

    private boolean isFoodAttached;

    private SeatType seatType;

    private int seatPrice;


    @ManyToOne
    @JoinColumn
    private Shows shows;

    @ManyToOne
    @JoinColumn
    private Ticket ticket;
}
