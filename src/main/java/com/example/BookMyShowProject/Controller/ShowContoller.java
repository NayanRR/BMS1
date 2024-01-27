package com.example.BookMyShowProject.Controller;

import com.example.BookMyShowProject.DTOs.AddShowRequest;
import com.example.BookMyShowProject.Service.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

@RestController
@RequestMapping("/show")
@Slf4j
public class ShowContoller {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    private ResponseEntity<String> AddShow(@RequestBody AddShowRequest addShowRequest)  {
        try{
            log.info("We have request : {}",addShowRequest.toString());
            System.out.println(addShowRequest.getMovieName());
            System.out.println(addShowRequest.getShowDate());
            System.out.println(addShowRequest.getNormalSeatPrice());
            System.out.println(addShowRequest.getTheatreId());
            System.out.println(addShowRequest.getScreenId());
            String res=showService.addShow(addShowRequest);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
