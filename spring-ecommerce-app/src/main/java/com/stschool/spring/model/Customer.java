package com.stschool.spring.model;

import com.stschool.spring.enums.Gender;
import com.stschool.spring.enums.Membership;
import com.stschool.spring.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder

public class Customer {
    private int id;
    private String name;
    private  String email;
    private String phoneNo;
    private String password;
    private byte age;
    private Gender gender;
    private Status status;
    private Membership membership;
    private LocalDateTime createdOn;
    private LocalDateTime lastLoggedIn;
    private Address residentialAddress;
    private Address shippingAddress;
}
