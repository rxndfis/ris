package com.company.service;

import com.company.model.Customer;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface CustomerService {

    List<Customer> getAll();

    List<Customer> filterByCountry(String country);

    Customer searchCustomerByID(Long id);
}