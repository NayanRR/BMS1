package com.example.BookMyShowProject.Service;

import com.example.BookMyShowProject.DTOs.AddShowRequest;
import com.example.BookMyShowProject.Enums.SeatType;
import com.example.BookMyShowProject.Exception.MovieNotFoundException;
import com.example.BookMyShowProject.Exception.TheatreNotFoundException;
import com.example.BookMyShowProject.Exception.TheatreScreenNotFoundException;
import com.example.BookMyShowProject.Models.*;
import com.example.BookMyShowProject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ScreenRepository screenRepository;


    public String addShow(AddShowRequest addShowRequest) throws Exception {

        Optional<Theatre> optionalTheatre=theatreRepository.findById(addShowRequest.getTheatreId());
        if(!optionalTheatre.isPresent()){
            //Throw Exception
            throw new TheatreNotFoundException("Theatre Id is Invalid");
        }
        Theatre theatre=optionalTheatre.get();

       Optional<TheatreScreen> optionalTheatreScreen=screenRepository.findById(addShowRequest.getScreenId());
       if(!optionalTheatreScreen.isPresent()){
           //Throw Exception
           throw new TheatreScreenNotFoundException("Theatre Screen Id is Invalid");
       }
       TheatreScreen theatreScreen=optionalTheatreScreen.get();

       Optional<Movie> optionalMovie= movieRepository.findMovieByMovieName(addShowRequest.getMovieName());
       if(!optionalMovie.isPresent()){
           //Throw Exception
           throw new MovieNotFoundException("Movie name is invalid");
       }
       Movie movie=optionalMovie.get();

       Shows show=new Shows().builder()
                   .startTime(addShowRequest.getStartTime())
                   .endTime(addShowRequest.getEndTime())
                    .showDate(addShowRequest.getShowDate())
                   .build();

       //Bidirectionally mapping between show and movie
       show.setMovie(movie);
       movie.getShowsList().add(show);

        //Bidirectionally mapping between show and Screen
       show.setTheatreScreen(theatreScreen);
       theatreScreen.getShowsList().add(show);

        List<ShowSeats> showSeatslist=new ArrayList<>();
       //Theatre seat ko copy kar raha hu show seat
       for(TheatreSeat theatreSeat:theatreScreen.getTheatreSeatList()){
           ShowSeats showSeats=new ShowSeats();
           showSeats.setSeatNo(theatreSeat.getSeatNo());
           showSeats.setAvailable(TRUE);
           showSeats.setFoodAttached(FALSE);

           showSeats.setShows(show);






           if(theatreSeat.getSeatType().toString()=="NORMAL"){
               showSeats.setSeatType(SeatType.NORMAL);
               showSeats.setSeatPrice(addShowRequest.getNormalSeatPrice());
           }else{
               showSeats.setSeatType(SeatType.PREMIUM);
                showSeats.setSeatPrice(addShowRequest.getPremiumSeatPrice());
           }

           showSeatslist.add(showSeats);
           showSeatsRepository.save(showSeats);

       }
       show.setShowSeatsList(showSeatslist);
       showRepository.save(show);

       return "The show is added successfully with showId"+show.getShowId();

        }
}
