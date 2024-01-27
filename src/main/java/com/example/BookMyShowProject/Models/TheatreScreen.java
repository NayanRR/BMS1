package com.example.BookMyShowProject.Models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.ArrayList;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TheatreScreen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screenId;

    private int noOfRowSeats;

    private int noOfPremiumSeats;

    private int noOfNormalSeats;

    //Many Screen can be part of one Theatre
    @ManyToOne
    @JoinColumn
    private Theatre theatre;

    //one Screen can have many shows
    @OneToMany(mappedBy = "theatreScreen",cascade = CascadeType.ALL)
    private List<Shows> showsList=new ArrayList<>();

    //one Screen can have many TheatreSeat
    @OneToMany(mappedBy="theatreScreen",cascade = CascadeType.ALL)
    private List<TheatreSeat> theatreSeatList=new ArrayList<>();

}
