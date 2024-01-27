package com.example.BookMyShowProject.Service;

import com.example.BookMyShowProject.DTOs.AddTicketRequest;
import com.example.BookMyShowProject.Models.*;
import com.example.BookMyShowProject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TheatreRepository theatreRepository;
    public String bookTicket(AddTicketRequest addTicketRequest) {
        TheatreUser theatreUser=userRepository.findById(addTicketRequest.getUserId()).get();
        Theatre theatre=theatreRepository.findById(addTicketRequest.getTheatreId()).get();

        String movieName=addTicketRequest.getMovieName();
        String city=addTicketRequest.getCity();
        LocalDate showDate=addTicketRequest.getShowDate();
        LocalTime showTime=addTicketRequest.getShowTime();


        List<Shows> showsList=findRightShow(movieName,city,showDate,showTime);

       Shows shows1=null;

        for(Shows show:showsList){

            if(show.getTheatreScreen().getTheatre()==theatre){
                //you got the required show
                shows1=show;
                break;
            }
        }

        List<ShowSeats> showSeatsList=shows1.getShowSeatsList();
        List<String> requestedSeats=addTicketRequest.getRequestedSeatNos();

        int cost=0;

     List<ShowSeats> bookedShowSeats=new ArrayList<>();
        Ticket ticket=null;
        for(ShowSeats showSeats:showSeatsList){
            if(requestedSeats.contains(showSeats.getSeatNo())){
                if(showSeats.isAvailable()==true){
                    ticket = CreateNewTicket(movieName, theatre, shows1, addTicketRequest);
                    showSeats.setAvailable(false);
                    cost=cost+showSeats.getSeatPrice();
                    //Bidirection mapping between show seat and ticket
                    bookedShowSeats.add(showSeats);
                    showSeats.setTicket(ticket);
                }else{
                    return "The seats you are looking for is already booked";
                }

            }
      }

      ticket.setTicketCost(cost);
      ticket.setShowSeatsList(bookedShowSeats);
      ticketRepository.save(ticket);
      return "Ticket is Successfully booked for movie "+shows1.getMovie()+" "+"in theatre"+ticket.getTheatreName();


    }

    private Ticket CreateNewTicket(String movieName, Theatre theatre, Shows shows1, AddTicketRequest addTicketRequest) {
        Ticket ticket=new Ticket().builder()
                .movieName(movieName)
                .theatreName(theatre.getName())
                .theatreAdd(theatre.getAddress())
                .screenNo(String.valueOf(shows1.getTheatreScreen().getScreenId()))
                .movieDate(addTicketRequest.getShowDate())
                .showTime(addTicketRequest.getShowTime())
                .build();

        return ticket;

    }

    private List<Shows> findRightShow(String movieName, String city, LocalDate showDate, LocalTime showTime) {
        Movie movie=movieRepository.findMovieByMovieName(movieName).get();
        List<Shows> showsList=showRepository.findShowsByMovieAndStartTimeAndShowDate(movie, showTime, showDate);
        return showsList;

    }
}
