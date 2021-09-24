package com.exp.Ass05Thymeleaf.data_access;

import com.exp.Ass05Thymeleaf.models.Customer;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface CustomerRepo {
    public ArrayList<Customer> getAllCustomers();
    public Customer getCustomerById(String custId);
    public Boolean addCustomer(Customer customer);
    public Boolean updateCustomer(Customer customer);
}
