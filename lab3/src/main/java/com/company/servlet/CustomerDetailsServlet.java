package com.company.servlet;

import com.company.ParseUtils;
import com.company.model.Customer;
import com.company.service.CustomerService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerDetailsServlet", value = "/customers")
public class CustomerDetailsServlet extends HttpServlet {

    @EJB
    private CustomerService customerService;

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameField = req.getParameter("country");
        String idField = req.getParameter("id");
        List<Customer> customers;
        if (nameField == null || nameField.equals("")) {
            customers = customerService.getAll();
        } else {
            customers = customerService.filterByCountry(nameField);
        }

        Customer customerSearchResult = searchCustomer(idField);

        if (customerSearchResult != null) {
            req.setAttribute("searchResult", List.of(customerSearchResult));
        }

        req.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/customers.jsp").forward(req, resp);
    }

    private Customer searchCustomer(String idFieldValue) {
        if (idFieldValue == null || idFieldValue.isBlank())
            return null;

        Long idToSearch = ParseUtils.safeParseLong(idFieldValue);

        if (idToSearch == null) return null;

        return customerService.searchCustomerByID(idToSearch);
    }

    public void destroy() {
    }
}