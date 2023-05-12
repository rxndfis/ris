package com.example.lab5.dao;

import com.example.lab5.model.Customer;
import com.example.lab5.util.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CustomerDao implements Dao<Customer, Long> {

    private final EntityManagerFactory entityManagerFactory;

    private static final String PERSISTENCE_UNIT_NAME = "CustomerManagement";

    public CustomerDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public Customer get(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Customer.class, id);
        } finally {
            entityManager.close();
        }
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

    @Override
    public void save(Customer entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = entityManager.find(Customer.class, id);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(customer);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void update(Customer updated) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, updated.getId());
        Utils.setIfNotNull(updated.getFullName(), customer::setFullName);
        Utils.setIfNotNull(updated.getCountry(), customer::setCountry);
        Utils.setIfNotNull(updated.getState(), customer::setState);
        Utils.setIfNotNull(updated.getAddress(), customer::setAddress);
        Utils.setIfNotNull(updated.getTelephone(), customer::setTelephone);
        entityManager.merge(customer);
        transaction.commit();
        entityManager.close();
    }
}
