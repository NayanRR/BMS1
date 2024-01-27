package com.example.BookMyShowProject.Security;

import com.example.BookMyShowProject.Models.TheatreUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {

    private TheatreUser theatreUser;

    public CustomUserDetails(TheatreUser theatreUser) {
        super();
        this.theatreUser=theatreUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(theatreUser.getRole().toString());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return theatreUser.getPassword();
    }

    @Override
    public String getUsername() {
        return theatreUser.getEmailId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
