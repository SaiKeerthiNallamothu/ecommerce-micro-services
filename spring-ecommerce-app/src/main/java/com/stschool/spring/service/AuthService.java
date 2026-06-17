package com.stschool.spring.service;

import com.stschool.spring.expection.CustomerExistsException;
import com.stschool.spring.expection.CustomerNotFoundException;
import com.stschool.spring.model.Customer;

public interface AuthService {
    Customer signup(Customer customer) throws CustomerExistsException;

    Customer login(String email, String password) throws InvalidCredentialsException;

    Customer getCustomerByEmail(String email) throws CustomerNotFoundException;
}
