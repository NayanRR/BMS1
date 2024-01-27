package com.example.BookMyShowProject.Service;

import com.example.BookMyShowProject.DTOs.AddScreenRequest;
import com.example.BookMyShowProject.DTOs.AddTheatreRequest;
import com.example.BookMyShowProject.Enums.SeatType;
import com.example.BookMyShowProject.Models.Theatre;
import com.example.BookMyShowProject.Models.TheatreScreen;
import com.example.BookMyShowProject.Models.TheatreSeat;
import com.example.BookMyShowProject.Repository.ScreenRepository;
import com.example.BookMyShowProject.Repository.TheatreRepository;
import com.example.BookMyShowProject.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    TheatreSeatRepository theatreSeatRepository;
    public String addTheatre(AddTheatreRequest addTheatreRequest) {
         Theatre theatre=new Theatre().builder()
                         .name(addTheatreRequest.getName())
                         .city(addTheatreRequest.getCity())
                         .noOfScreen(addTheatreRequest.getNoOfScreen())
                         .address(addTheatreRequest.getAddress()).build();

         theatreRepository.save(theatre);
         return "Theatre is added successfully in DB";
    }

    public String addScreen(AddScreenRequest addScreenRequest) {
        Theatre theatre=theatreRepository.findById(addScreenRequest.getTheatreId()).get();

        int noOfSeatsInEachRow=addScreenRequest.getNoOfRowSeats();
        int normalSeats= addScreenRequest.getNoOfNormalSeats();
        int premiumSeats= addScreenRequest.getNoOfPremiumSeats();



        TheatreScreen theatreScreen=new TheatreScreen().builder()
                                    .noOfNormalSeats(normalSeats)
                                    .noOfPremiumSeats(premiumSeats)
                                    .noOfRowSeats(noOfSeatsInEachRow)
                                    .build();

        //Theatre screen and Thetare Bidrectional mapping
        theatreScreen.setTheatre(theatre);
        theatre.getTheatreScreenList().add(theatreScreen);

        // Adding Normal Seats
        int row=0;
        int noOfRowsInNormaSeats=normalSeats/noOfSeatsInEachRow;
        if(normalSeats%noOfSeatsInEachRow!=0){
            //That means normalseats is not divisible
            noOfRowsInNormaSeats++;
        }

        int i=1;
        char ch;

        List<TheatreSeat> theatreSeats=new ArrayList<>();

        for(ch='A';ch<='Z'&& i<=noOfRowsInNormaSeats;ch++,i++){
            for(int j=1;j<=noOfSeatsInEachRow && normalSeats>0;j++){
                String s=j+""+ch;
                TheatreSeat theatreSeat=new TheatreSeat().builder().seatNo(s).seatType(SeatType.NORMAL).build();
                //Bidirectional mapping
                theatreSeat.setTheatreScreen(theatreScreen);
                theatreSeats.add(theatreSeat);
                normalSeats--;
                theatreSeatRepository.save(theatreSeat);
            }
        }

        //Adding Premium Seats

        int noOfRowsInPremiumSeats=premiumSeats/noOfSeatsInEachRow;
        if(premiumSeats%noOfSeatsInEachRow!=0){
            //That means normalseats is not divisible
            noOfRowsInPremiumSeats++;
        }
        i=1;
        for(;ch<='Z'&& i<=noOfRowsInPremiumSeats;ch++,i++){
            for(int j=1;j<=noOfSeatsInEachRow && premiumSeats>0;j++){
                String s=j+""+ch;
                TheatreSeat theatreSeat=new TheatreSeat().builder().seatNo(s).seatType(SeatType.PREMIUM).build();
                //Bidirectional mapping
                theatreSeat.setTheatreScreen(theatreScreen);
                theatreSeats.add(theatreSeat);
                premiumSeats--;
                theatreSeatRepository.save(theatreSeat);
            }
        }

        theatreScreen.setTheatreSeatList(theatreSeats);

        screenRepository.save(theatreScreen);
        return "Theatre Screen and its Seat is added Successfully";

    }
}
