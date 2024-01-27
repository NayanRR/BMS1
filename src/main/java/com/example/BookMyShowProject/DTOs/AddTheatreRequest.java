package com.example.BookMyShowProject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheatreRequest {
    private String name;

    private String city;

    private String address;

    private int noOfScreen;
}
