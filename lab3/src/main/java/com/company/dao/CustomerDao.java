package com.company.dao;

import com.company.model.Customer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

@Stateless
public class CustomerDao implements Dao<Customer, Long> {

    private final EntityManagerFactory entityManagerFactory;

    public CustomerDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("CustomerManagement");
    }

    @Override
    public List<Customer> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> getAll = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> root = getAll.from(Customer.class);
        getAll.select(root);
        try {
            return entityManager.createQuery(getAll).getResultList();
        } finally {
            entityManager.close();
        }
    }
}