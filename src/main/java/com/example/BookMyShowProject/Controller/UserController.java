package com.example.BookMyShowProject.Controller;

import com.example.BookMyShowProject.DTOs.AddUserRequest;
import com.example.BookMyShowProject.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@Slf4j
@EnableMethodSecurity
public class UserController {

    @Autowired
    UserService userService;


    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/normal")
    public String Trial1(){
        return "This is for normal user";
    }
    @GetMapping("/public")
    public String Trial2(){
        return "This is for public user";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String Trial3(){
        return "This is for Admin user";
    }
    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        try{
            log.info("We have request : {}",addUserRequest.toString());
           String res= userService.addUser(addUserRequest);
           return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
