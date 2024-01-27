package com.example.BookMyShowProject.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddScreenRequest {

    private int theatreId;

    private int noOfRowSeats;

    private int noOfPremiumSeats;

    private int noOfNormalSeats;
}
