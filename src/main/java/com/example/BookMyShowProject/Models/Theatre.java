package com.example.BookMyShowProject.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theatreId;

    private String name;

    private String city;

    @Column(unique = true,nullable = false)
    private String address;

    private int noOfScreen;

    //One Theatre can Have Many Screen
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    private List<TheatreScreen> theatreScreenList=new ArrayList<>();


}
