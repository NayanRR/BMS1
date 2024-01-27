package com.example.BookMyShowProject.Models;

import com.example.BookMyShowProject.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

//public class TheatreUser implements UserDetails
public class TheatreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;

    private String userName;

    private String mobNo;

    private String emailId;

    private int age;

    private String city;

    private String password;

    private Role role;

    //One User can have Many Tickets
    @OneToMany(mappedBy = "theatreUser",cascade = CascadeType.ALL)
    private List<Ticket> ticketList=new ArrayList<>();


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("USER");
//        authorities.add(simpleGrantedAuthority);
//        return authorities;
//    }

//    @Override
//    public String getPassword() {
//        return this.getPassword();
//    }
//
//
//    @Override
//    public String getUsername() {
//        return this.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
