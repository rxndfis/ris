package com.example.lab5.service;

import com.example.lab5.dao.CustomerDao;
import com.example.lab5.model.Customer;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;

import java.util.List;

@ManagedBean(name = "customerBean")
public class CustomerBean {

    private Long id;
    private String fullName;
    private String country;
    private String state;
    private String address;
    private String telephone;

    private String editCustomerId;

    public String createNewCustomer(CustomerBean customer) {
        Customer toBeSaved = new Customer(null, customer.fullName, customer.country,
                customer.state, customer.address, customer.telephone);
        CustomerDao customerDao = new CustomerDao();
        customerDao.save(toBeSaved);
        return "customers.xhtml?faces-redirect=true";
    }
    // todo: Pagination (list, кнопки для выбора кол-ва элементов на странице),
    // валидация на обновление и добавление элемента

    public List<Customer> getCustomers() {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.getAll();
    }

    public String updateCustomer(CustomerBean customer) {
        Customer inEdit = new Customer(Long.valueOf(customer.editCustomerId), customer.fullName, customer.country,
                customer.state, customer.address, customer.telephone);
        CustomerDao customerDao = new CustomerDao();
        customerDao.update(inEdit);
        return "customers.xhtml?faces-redirect=true";
    }

    public String editCustomer() {
        editCustomerId = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap().get("selectedCustomerId");
        return "customerEdit.xhtml";
    }

    public String deleteCustomer(long customerId) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.delete(customerId);
        return "customers.xhtml?faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEditCustomerId() {
        return editCustomerId;
    }

    public void setEditCustomerId(String editCustomerId) {
        this.editCustomerId = editCustomerId;
    }
}
