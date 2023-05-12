package com.company.service;

import com.company.dao.Dao;
import com.company.model.Customer;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CustomerServiceImpl implements CustomerService {

    @EJB
    private Dao<Customer, Long> customerDao;

    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public List<Customer> filterByCountry(String country) {
        return customerDao.getAll().stream()
                .filter((it) -> it.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    @Override
    public Customer searchCustomerByID(Long id) {
        return customerDao.getAll().stream()
                .filter((it) -> it.getId().equals(id)) // todo: parse long instead toString
                .findFirst()
                .orElse(null);
    }
}