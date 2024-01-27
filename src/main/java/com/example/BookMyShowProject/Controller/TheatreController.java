package com.example.BookMyShowProject.Controller;

import com.example.BookMyShowProject.DTOs.AddScreenRequest;
import com.example.BookMyShowProject.DTOs.AddTheatreRequest;
import com.example.BookMyShowProject.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/addTheatre")
    public ResponseEntity<String> addTheatre(@RequestBody AddTheatreRequest addTheatreRequest)  {
        try{
            String res=theatreService.addTheatre(addTheatreRequest);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            //Throw exception
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addScreen")
    public ResponseEntity<String> addTheatreScreen(@RequestBody AddScreenRequest addScreenRequest)  {
        try{
            String res=theatreService.addScreen(addScreenRequest);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
