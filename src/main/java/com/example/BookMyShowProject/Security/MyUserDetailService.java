package com.example.BookMyShowProject.Security;

import com.example.BookMyShowProject.Models.TheatreUser;
import com.example.BookMyShowProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Component
//public class MyUserDetailService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        TheatreUser theatreUser =userRepository.findUserByUserName(username);
//        if(theatreUser==null){
//            throw new UsernameNotFoundException("User Not Found");
//        }
//        return theatreUser;
//    }
//}
