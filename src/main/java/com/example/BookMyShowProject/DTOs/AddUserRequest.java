package com.example.BookMyShowProject.DTOs;

import com.example.BookMyShowProject.Enums.Role;
import com.example.BookMyShowProject.Models.Ticket;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddUserRequest {

    //Using hibernate validator so it will directly validate all the data coming in right format
    //regexp-Stands for regular expression
    //This hibernate validator will generate Methodargument Nor Valid Exception if someone puts invalid data
    @NotEmpty
    @Size(min=4,message = "Username must be minimum of 4 characters !!")
    private String userName;

//    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String mobNo;

    @Email
    @NotNull
    @NotEmpty(message = "Email Id is not valid !!")
    private String emailId;


    @Min(value = 0, message = "Invalid Age: Equals to zero or Less than zero")
    @Max(value = 100, message = "Invalid Age: Exceeds 100 years")
    private int age;

    @NotEmpty
    private String city;

    @NotEmpty
    private Role role;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
//    Min 1 uppercase letter.
//    Min 1 lowercase letter.
//    Min 1 special character.
//    Min 1 number.
//    Min 8 characters.
    private String password;


}
