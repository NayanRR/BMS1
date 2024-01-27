package com.example.BookMyShowProject.Service;

import com.example.BookMyShowProject.DTOs.AddUserRequest;
import com.example.BookMyShowProject.Models.TheatreUser;
import com.example.BookMyShowProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public String addUser(AddUserRequest addUserRequest) {

        String password=passwordEncoder.encode(addUserRequest.getPassword());
        TheatreUser theatreUser=new TheatreUser().builder()
                                .userName(addUserRequest.getUserName())
                                .age(addUserRequest.getAge())
                                .mobNo(addUserRequest.getMobNo())
                                .password(password).role(addUserRequest.getRole())
                                .city(addUserRequest.getCity())
                                .emailId(addUserRequest.getEmailId()).build();

        userRepository.save(theatreUser);
        return "The user is successfully added in db";
    }
}
