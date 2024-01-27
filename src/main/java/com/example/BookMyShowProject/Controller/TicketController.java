package com.example.BookMyShowProject.Controller;

import com.example.BookMyShowProject.DTOs.AddTicketRequest;
import com.example.BookMyShowProject.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User/Ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/BookTicket")
    public ResponseEntity<String> bookTicket(@RequestBody AddTicketRequest addTicketRequest){
        try{
            String res=ticketService.bookTicket(addTicketRequest);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
